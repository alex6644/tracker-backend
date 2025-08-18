package tracker.trackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.trackerback.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {}
