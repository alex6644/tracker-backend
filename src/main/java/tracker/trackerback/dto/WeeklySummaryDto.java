package tracker.trackerback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeeklySummaryDto {
    private String weekLabel; // z.B. "Diese", "Letzte", "KW 33"
    private int workoutCount; // Anzahl der Workouts in dieser Woche
    private double totalVolume; // Summe aller Gewichte * Wiederholungen
}