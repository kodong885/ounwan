package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.workout.entity.enums.WorkoutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_sessions")
@Builder
@Getter
public class WorkoutSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_record_id")
    private WorkoutRecord workoutRecord;

    @OneToMany(mappedBy = "workoutSession")
    private List<WorkoutExercise> workoutExercises;

    @Column(name = "workout_at", nullable = false)
    private LocalTime workoutAt;

    @Column(name = "workout_type", nullable = false)
    private WorkoutType workoutType;

    @Column(name = "memo", nullable = true)
    private String memo;

}
