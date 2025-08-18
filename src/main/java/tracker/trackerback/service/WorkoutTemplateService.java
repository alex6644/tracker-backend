package tracker.trackerback.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tracker.trackerback.dto.WorkoutTemplateDto;
import tracker.trackerback.model.Exercise;
import tracker.trackerback.model.TemplateExercise;
import tracker.trackerback.model.User;
import tracker.trackerback.model.WorkoutTemplate;
import tracker.trackerback.repository.ExerciseRepository;
import tracker.trackerback.repository.WorkoutTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkoutTemplateService {

    private final WorkoutTemplateRepository templateRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public WorkoutTemplate createTemplate(WorkoutTemplateDto dto) {
        User currentUser = getCurrentUser();
        WorkoutTemplate template = new WorkoutTemplate();
        template.setName(dto.getName());
        template.setDescription(dto.getDescription());
        template.setUser(currentUser);

        List<TemplateExercise> templateExercises = new ArrayList<>();
        dto.getExercises().forEach(exDto -> {
            Exercise exercise = exerciseRepository.findById(exDto.getExerciseId())
                    .orElseThrow(() -> new NoSuchElementException("Exercise not found"));
            TemplateExercise te = new TemplateExercise();
            te.setExercise(exercise);
            te.setTargetSets(exDto.getTargetSets());
            te.setTargetReps(exDto.getTargetReps());
            te.setDisplayOrder(exDto.getDisplayOrder());
            te.setTemplate(template);
            templateExercises.add(te);
        });

        template.setTemplateExercises(templateExercises);
        return templateRepository.save(template);
    }

    public List<WorkoutTemplate> getMyTemplates() {
        User currentUser = getCurrentUser();
        return templateRepository.findByUserId(currentUser.getId());
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
