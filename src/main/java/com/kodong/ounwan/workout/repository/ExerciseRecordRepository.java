package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.workout.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
}