package com.kodong.ounwan.workout.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exercise_sets")
public class ExerciseSet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_record_id", nullable = false)
    private ExerciseRecord exerciseRecord;

    @Column(nullable = false)
    private Integer setNumber;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Integer reps;

    private String description;

    public ExerciseSet(
            ExerciseRecord exerciseRecord,
            Integer setNumber,
            Double weight,
            Integer reps,
            String description
    ) {
        this.exerciseRecord = exerciseRecord;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
        this.description = description;
    }
}