package tracker.trackerback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
public class DashboardDto {
    private String username; // Für "Hallo, Alexander"
    private List<WeeklySummaryDto> weeklySummary; // Für das 6-Wochen-Chart
    private List<WorkoutTemplateSummaryDto> templates; // Für die Vorlagen-Karten
    private List<WorkoutHistoryDto> history; // Für die Verlaufsliste
}