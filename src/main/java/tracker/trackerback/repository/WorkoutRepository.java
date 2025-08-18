package tracker.trackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.trackerback.model.Workout;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserIdOrderByStartTimeDesc(Long userId);
}
