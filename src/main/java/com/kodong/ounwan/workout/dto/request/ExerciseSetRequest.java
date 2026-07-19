package com.kodong.ounwan.workout.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExerciseSetRequest {

    private Integer setNumber;

    private Double weight;

    private Integer reps;

    private String description;

}