package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.workout.entity.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {

}
