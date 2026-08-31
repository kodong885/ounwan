package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.workout.entity.enums.WorkoutBodyPart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_sessions")
@Builder
@Getter
public class WorkoutSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_record_id")
    private WorkoutRecord workoutRecord;

    @OneToMany(
            mappedBy = "workoutSession", // mappedBy : 연관관계의 주인이 누구인지 알려주는 속성 (WorkoutExercise.java에 있는 workoutSession필드)
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutExercise> workoutExercises = new ArrayList<WorkoutExercise>();

    @Column(name = "workout_at", nullable = false)
    private LocalTime workoutAt;

    @Column(name = "workout_duration_minutes", nullable = false)
    private Integer workoutDurationMinutes;

    @ElementCollection
    @CollectionTable(
            name = "workout_session_body_parts",
            joinColumns = @JoinColumn(name = "workout_session_id")
    )
    @Column(name = "body_part")
    @Enumerated(EnumType.STRING)
    private Set<WorkoutBodyPart> workoutBodyParts = new HashSet<>();

    @Column(name = "memo", nullable = true)
    private String memo;

    public void addWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercises.add(workoutExercise);
    }

    public static WorkoutSession create(WorkoutRecord workoutRecord, LocalTime workoutAt, Integer workoutDurationMinutes, Set<WorkoutBodyPart> workoutBodyParts, String memo) {
        return WorkoutSession.builder()
                .workoutRecord(workoutRecord)
                .workoutAt(workoutAt)
                .workoutDurationMinutes(workoutDurationMinutes)
                .workoutBodyParts(workoutBodyParts)
                .memo(memo)
                .build();
    }

}
