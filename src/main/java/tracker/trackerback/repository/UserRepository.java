package tracker.trackerback.repository; // Or your preferred repository package

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.trackerback.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}