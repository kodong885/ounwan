package com.kodong.ounwan.workout.dto;


import com.kodong.ounwan.workout.entity.enums.WorkoutBodyPart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutSessionDto {

    private List<WorkoutExerciseDto> workoutExerciseDtos;

    private LocalTime workoutAt;

    private Integer workoutDurationMinutes;

    private Set<WorkoutBodyPart> workoutBodyParts;

    private String memo;

}
