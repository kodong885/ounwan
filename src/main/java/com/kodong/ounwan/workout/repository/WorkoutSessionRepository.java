package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.workout.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    @Query("""
        SELECT DISTINCT ws
        FROM WorkoutSession ws
        JOIN ws.workoutRecord wr
        LEFT JOIN FETCH ws.workoutExercises
        LEFT JOIN FETCH ws.workoutBodyParts
        WHERE ws.id = :workoutSessionId
          AND wr.id = :workoutRecordId
          AND wr.user = :currentUser
        """)
    Optional<WorkoutSession> findWithExercises(
            @Param("currentUser") User currentUser,
            @Param("workoutRecordId") Long workoutRecordId,
            @Param("workoutSessionId") Long workoutSessionId
    );

}
