package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.workout.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

}
