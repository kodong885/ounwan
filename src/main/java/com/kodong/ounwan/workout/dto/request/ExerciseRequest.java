package com.kodong.ounwan.workout.dto.request;


import com.kodong.ounwan.workout.entity.ExerciseType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExerciseRequest {

    private ExerciseType exerciseType;

    private List<ExerciseSetRequest> sets;

}