package tracker.trackerback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TemplateExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int targetSets;
    private String targetReps; // e.g., "8-12"
    private int displayOrder;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    @JsonIgnore
    private WorkoutTemplate template;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}