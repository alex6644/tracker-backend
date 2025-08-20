package tracker.trackerback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutTemplateSummaryDto {
    private Long id;
    private String name;
    private String description;
    private int exerciseCount;
}