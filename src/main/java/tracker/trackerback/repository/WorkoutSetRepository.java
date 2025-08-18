package tracker.trackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.trackerback.model.WorkoutSet;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
}
