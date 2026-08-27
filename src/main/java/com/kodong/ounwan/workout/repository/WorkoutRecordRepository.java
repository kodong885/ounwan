package com.kodong.ounwan.workout.repository;

import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.workout.entity.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {

    @Query("""
        SELECT DISTINCT wr
        FROM WorkoutRecord wr
        LEFT JOIN FETCH wr.workoutSessions ws
        WHERE wr.id = :workoutRecordId
          AND wr.user = :currentUser
        """)
    Optional<WorkoutRecord> findWithSessions(
            @Param("currentUser") User currentUser,
            @Param("workoutRecordId") Long workoutRecordId
    );

}
