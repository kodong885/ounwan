package com.kodong.ounwan.workout.dto.response;


import com.kodong.ounwan.workout.entity.WorkoutRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkoutRecordResponse {

    private WorkoutRecord workoutRecord;

    public static WorkoutRecordResponse from(WorkoutRecord workoutRecord) {
        return new WorkoutRecordResponse(workoutRecord);
    }

}
