package tracker.trackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.trackerback.model.WorkoutTemplate;

import java.util.List;

public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplate, Long> {
    List<WorkoutTemplate> findByUserId(Long userId);
}
