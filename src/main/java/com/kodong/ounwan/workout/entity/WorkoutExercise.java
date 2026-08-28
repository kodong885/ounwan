package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.workout.entity.enums.WorkoutExerciseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_exercises")
@Builder
@Getter
public class WorkoutExercise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_exercise_type", nullable = false)
    private WorkoutExerciseType workoutExerciseType;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "reps", nullable = false)
    private int reps;

    public static WorkoutExercise create(WorkoutSession workoutSession, WorkoutExerciseType workoutExerciseType, int sets, int reps) {
        return WorkoutExercise.builder()
                .workoutSession(workoutSession)
                .workoutExerciseType(workoutExerciseType)
                .sets(sets)
                .reps(reps)
                .build();
    }

}
