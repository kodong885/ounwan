package com.kodong.ounwan.workout.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutRecordRequest {

    private LocalDate workoutDate;

    private String memo;

    private List<ExerciseRequest> exercises;
}