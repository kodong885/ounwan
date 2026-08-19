package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.workout.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

}
