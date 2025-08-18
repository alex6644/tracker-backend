package tracker.trackerback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tracker.trackerback.model.*;
import tracker.trackerback.repository.*;

import java.util.List;

/**
 * Service für die Verwaltung der Übungsbibliothek.
 */
@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}