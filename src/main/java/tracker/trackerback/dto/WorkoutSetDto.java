package tracker.trackerback.dto;

import lombok.Data;

@Data
public class WorkoutSetDto {
    private Long exerciseId;
    private int reps;
    private double weight;
}

