package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.workout.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    @Query("""
        SELECT we
        FROM WorkoutExercise we
        JOIN we.workoutSession ws
        JOIN ws.workoutRecord wr
        WHERE we.id = :workoutExerciseId
          AND ws.id = :workoutSessionId
          AND wr.id = :workoutRecordId
          AND wr.user = :currentUser
        """)
    Optional<WorkoutExercise> findByRecordPath(
            @Param("currentUser") User currentUser,
            @Param("workoutRecordId") Long workoutRecordId,
            @Param("workoutSessionId") Long workoutSessionId,
            @Param("workoutExerciseId") Long workoutExerciseId
    );

}
