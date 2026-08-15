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
@Table(name = "workout_exercises_cardio")
@Builder
@Getter
public class WorkoutExerciseCardio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private WorkoutExerciseType workoutExerciseType; // 유산소(cardio)

}
