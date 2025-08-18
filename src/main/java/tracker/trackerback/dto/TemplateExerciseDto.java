package tracker.trackerback.dto;

import lombok.Data;

@Data
public class TemplateExerciseDto {
    private Long exerciseId;
    private int targetSets;
    private String targetReps;
    private int displayOrder;
}