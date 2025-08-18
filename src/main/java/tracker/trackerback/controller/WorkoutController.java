package tracker.trackerback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.trackerback.dto.WorkoutSetDto;
import tracker.trackerback.model.Workout;
import tracker.trackerback.model.WorkoutSet;
import tracker.trackerback.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;

    @PostMapping("/start/from-template/{templateId}")
    public ResponseEntity<Workout> startWorkout(@PathVariable Long templateId) {
        return ResponseEntity.ok(workoutService.startWorkoutFromTemplate(templateId));
    }

    @PostMapping("/{workoutId}/sets")
    public ResponseEntity<WorkoutSet> addSet(@PathVariable Long workoutId, @RequestBody WorkoutSetDto dto) {
        return ResponseEntity.ok(workoutService.addSetToWorkout(workoutId, dto));
    }

    @PutMapping("/{workoutId}/finish")
    public ResponseEntity<Workout> finishWorkout(@PathVariable Long workoutId) {
        return ResponseEntity.ok(workoutService.finishWorkout(workoutId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Workout>> getHistory() {
        return ResponseEntity.ok(workoutService.getMyWorkoutHistory());
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> getWorkoutDetails(@PathVariable Long workoutId) {
        return ResponseEntity.ok(workoutService.getWorkoutDetails(workoutId));
    }
}