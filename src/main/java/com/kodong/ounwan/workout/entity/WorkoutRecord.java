package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_records")
@Builder
@Getter
public class WorkoutRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workoutRecord")
    private List<WorkoutSession> workoutSession;

    @Column(name = "workout_date", nullable = false)
    private LocalDate workoutDate;

}
