package com.kodong.ounwan.workout.service;


import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.user.repository.UserRepository;
import com.kodong.ounwan.workout.dto.WorkoutExerciseDto;
import com.kodong.ounwan.workout.dto.WorkoutRecordDto;
import com.kodong.ounwan.workout.dto.WorkoutSessionDto;
import com.kodong.ounwan.workout.entity.WorkoutExercise;
import com.kodong.ounwan.workout.entity.WorkoutRecord;
import com.kodong.ounwan.workout.entity.WorkoutSession;
import com.kodong.ounwan.workout.repository.WorkoutExerciseRepository;
import com.kodong.ounwan.workout.repository.WorkoutRecordRepository;
import com.kodong.ounwan.workout.repository.WorkoutSessionRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final UserRepository userRepository;
    private final WorkoutRecordRepository workoutRecordRepository;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    // TODO (반환값 수정 필요)
    @Transactional
    public void createWorkoutRecord(WorkoutRecordDto workoutRecordDto) {

        User user; // TODO

        LocalDate workoutDate = workoutRecordDto.getWorkoutDate();
        WorkoutRecord workoutRecord = new WorkoutRecord(user, workoutDate);
        workoutRecordRepository.save(workoutRecord);

        for (WorkoutSessionDto workoutSessionDto : workoutRecordDto.getWorkoutSessionDtos()) {
            WorkoutSession workoutSession = WorkoutSession.create(
                    workoutRecord,
                    workoutSessionDto.getWorkoutAt(),
                    workoutSessionDto.getWorkoutDurationMinutes(),
                    workoutSessionDto.getWorkoutBodyParts(),
                    workoutSessionDto.getMemo()
            );
            workoutSessionRepository.save(workoutSession);
            workoutRecord.addWorkoutSession(workoutSession);

            for (WorkoutExerciseDto workoutExerciseDto : workoutSessionDto.getWorkoutExerciseDtos()) {
                WorkoutExercise workoutExercise = WorkoutExercise.create(
                        workoutSession,
                        workoutExerciseDto.getWorkoutExerciseType(),
                        workoutExerciseDto.getSets(),
                        workoutExerciseDto.getSets()
                );
                workoutExerciseRepository.save(workoutExercise);
                workoutSession.addWorkoutExercise(workoutExercise);

            }
        }

    }

    // read
    // FIXME (매개변수 User currentUser 수정 필요)
    @Transactional(readOnly = true)
    public void findWorkoutRecord(User currentUser, Long workoutRecordId) {
        // WorkoutRecord, WorkoutSession, workoutExercise 전체 조회함

    }

    @Transactional(readOnly = true)
    public void findWorkoutSession(User currentUser, Long workoutRecordId, Long workoutSessionId) {

    }

    @Transactional(readOnly = true)
    public void findWorkoutExercise(User currentUser, Long workoutRecordId, Long workoutSessionId, Long workoutExerciseId) {

    }

    // update
    @Transactional
    public void updateWorkoutRecord();

    @Transactional
    public void updateWorkoutSession();

    @Transactional
    public void updateWorkoutExercise();

    // delete
    @Transactional
    public void deleteWorkoutRecord();

    @Transactional
    public void deleteWorkoutSession();

    @Transactional
    public void deleteWorkoutExercise();


}
