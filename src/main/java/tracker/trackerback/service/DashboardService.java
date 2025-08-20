package tracker.trackerback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tracker.trackerback.dto.*;
import tracker.trackerback.model.User;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {


    public DashboardDto getDashboardData() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DashboardDto dashboard = new DashboardDto();
        dashboard.setUsername(currentUser.getFirstName());

        dashboard.setWeeklySummary(createMockWeeklySummary());
        dashboard.setTemplates(createMockTemplates());
        dashboard.setHistory(createMockHistory());

        return dashboard;
    }

    private List<WeeklySummaryDto> createMockWeeklySummary() {
        return Arrays.asList(
                new WeeklySummaryDto("KW 30", 3, 25000),
                new WeeklySummaryDto("KW 31", 4, 28500),
                new WeeklySummaryDto("KW 32", 2, 19000),
                new WeeklySummaryDto("KW 33", 4, 31000),
                new WeeklySummaryDto("Letzte", 3, 27500),
                new WeeklySummaryDto("Diese", 2, 18000)
        );
    }

    private List<WorkoutTemplateSummaryDto> createMockTemplates() {
        return Arrays.asList(
                new WorkoutTemplateSummaryDto(1L, "Push Day A", "Fokus auf Brust, Schultern & Trizeps", 3),
                new WorkoutTemplateSummaryDto(2L, "Pull Day A", "Fokus auf Rücken & Bizeps", 4)
        );
    }

    private List<WorkoutHistoryDto> createMockHistory() {
        return Arrays.asList(
                new WorkoutHistoryDto(1L, "Push Day A", "Gestern"),
                new WorkoutHistoryDto(2L, "Leg Day", "Vor 3 Tagen")
        );
    }
}