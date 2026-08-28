package com.kodong.ounwan.workout.dto;

import com.kodong.ounwan.workout.entity.WorkoutExercise;
import com.kodong.ounwan.workout.entity.enums.WorkoutExerciseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutExerciseDto {

    private WorkoutExerciseType workoutExerciseType;

    private int sets;

    private int reps;

    public static WorkoutExerciseDto from(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseDto(
                workoutExercise.getWorkoutExerciseType(),
                workoutExercise.getSets(),
                workoutExercise.getReps()
                );
    }

}
