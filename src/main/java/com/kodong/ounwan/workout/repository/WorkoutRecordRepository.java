package com.kodong.ounwan.workout.repository;


import com.kodong.ounwan.workout.entity.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {

    List<WorkoutRecord> findAllById(Long userId);

    List<WorkoutRecord> findWorkoutRecordByUser_IdAndWorkoutDateBetweenOrderByWorkoutDateDesc(
            Long userId,
            LocalDate workoutDateAfter,
            LocalDate workoutDateBefore
    );

}
