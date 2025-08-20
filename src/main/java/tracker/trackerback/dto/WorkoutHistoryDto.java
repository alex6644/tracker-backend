package tracker.trackerback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class WorkoutHistoryDto {
    private Long id;
    private String name; // z.B. "Push Day A"
    private String date; // z.B. "Gestern", "Vor 3 Tagen"
}