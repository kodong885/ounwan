package com.kodong.ounwan.workout.service;


import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.workout.dto.WorkoutRecordDto;
import com.kodong.ounwan.workout.dto.WorkoutSessionDto;
import com.kodong.ounwan.workout.entity.WorkoutExercise;
import com.kodong.ounwan.workout.entity.WorkoutRecord;
import com.kodong.ounwan.workout.entity.WorkoutSession;
import com.kodong.ounwan.workout.repository.WorkoutExerciseRepository;
import com.kodong.ounwan.workout.repository.WorkoutRecordRepository;
import com.kodong.ounwan.workout.repository.WorkoutSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final WorkoutRecordRepository workoutRecordRepository;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Transactional
    public void createWorkoutRecord(WorkoutRecordDto workoutRecordDto) {

        User user; // TODO

        LocalDate workoutDate = workoutRecordDto.getWorkoutDate();
        WorkoutRecord workoutRecord = new WorkoutRecord(workoutDate);



    }

}
