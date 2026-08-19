package com.kodong.ounwan.workout.dto;

import com.kodong.ounwan.workout.entity.WorkoutSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutRecordDto {

    private LocalDate workoutDate;

    private List<WorkoutSessionDto> workoutSessionDtos;

}
