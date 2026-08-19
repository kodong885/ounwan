package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.workout.entity.enums.WorkoutBodyPart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_record_id")
    private WorkoutRecord workoutRecord;

    @OneToMany(mappedBy = "workoutSession")
    private List<WorkoutExercise> workoutExercises;

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

    // FIXME
    public static WorkoutSession create() {
        return WorkoutSession.builder()

    }

}
