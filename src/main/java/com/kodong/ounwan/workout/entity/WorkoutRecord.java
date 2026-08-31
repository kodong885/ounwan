package com.kodong.ounwan.workout.entity;


import com.kodong.ounwan.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_records")
@Builder
@Getter
public class WorkoutRecord {

    public WorkoutRecord(User user, LocalDate workoutDate) {
        this.user = user;
        this.workoutDate = workoutDate;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "workoutRecord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutSession> workoutSessions = new ArrayList<WorkoutSession>();

    @Column(name = "workout_date", nullable = false)
    private LocalDate workoutDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void addWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSessions.add(workoutSession);
    }

}
