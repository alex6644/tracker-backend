package tracker.trackerback.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tracker.trackerback.dto.WorkoutSetDto;
import tracker.trackerback.model.*;
import tracker.trackerback.repository.ExerciseRepository;
import tracker.trackerback.repository.WorkoutRepository;
import tracker.trackerback.repository.WorkoutSetRepository;
import tracker.trackerback.repository.WorkoutTemplateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutTemplateRepository templateRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Workout startWorkoutFromTemplate(Long templateId) {
        User currentUser = getCurrentUser();
        WorkoutTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new NoSuchElementException("Template not found"));

        Workout workout = new Workout();
        workout.setUser(currentUser);
        workout.setName(template.getName());
        workout.setStartTime(LocalDateTime.now());
        return workoutRepository.save(workout);
    }

    @Transactional
    public WorkoutSet addSetToWorkout(Long workoutId, WorkoutSetDto dto) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout not found"));
        Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                .orElseThrow(() -> new NoSuchElementException("Exercise not found"));

        if (!workout.getUser().getId().equals(getCurrentUser().getId())) {
            throw new SecurityException("User is not authorized for this workout");
        }

        WorkoutSet set = new WorkoutSet();
        set.setWorkout(workout);
        set.setExercise(exercise);
        set.setReps(dto.getReps());
        set.setWeight(dto.getWeight());
        set.setTimestamp(LocalDateTime.now());
        return workoutSetRepository.save(set);
    }

    @Transactional
    public Workout finishWorkout(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout not found"));

        if (!workout.getUser().getId().equals(getCurrentUser().getId())) {
            throw new SecurityException("User is not authorized for this workout");
        }

        workout.setEndTime(LocalDateTime.now());
        return workoutRepository.save(workout);
    }

    public List<Workout> getMyWorkoutHistory() {
        User currentUser = getCurrentUser();
        return workoutRepository.findByUserIdOrderByStartTimeDesc(currentUser.getId());
    }

    public Workout getWorkoutDetails(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout not found"));

        if (!workout.getUser().getId().equals(getCurrentUser().getId())) {
            throw new SecurityException("User is not authorized for this workout");
        }
        return workout;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
