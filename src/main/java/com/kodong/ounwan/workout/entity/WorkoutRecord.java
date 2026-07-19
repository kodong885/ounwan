package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "workout_records")
public class WorkoutRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate workoutDate;

    private String memo;

    public WorkoutRecord(
            User user,
            LocalDate workoutDate,
            String memo
    ) {
        this.user = user;
        this.workoutDate = workoutDate;
        this.memo = memo;
    }
}
