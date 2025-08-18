package tracker.trackerback.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkoutTemplateDto {
    private String name;
    private String description;
    private List<TemplateExerciseDto> exercises;
}
