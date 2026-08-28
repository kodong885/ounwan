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
import org.hibernate.boot.model.naming.IllegalIdentifierException;
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
    public WorkoutRecordDto createWorkoutRecord(WorkoutRecordDto workoutRecordDto) {

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
    public WorkoutRecordDto findWorkoutRecord(User currentUser, Long workoutRecordId) {
        // WorkoutRecord, WorkoutSession, workoutExercise 전체 조회함
        WorkoutRecord workoutRecord = workoutRecordRepository.findWithSessions(currentUser, workoutRecordId)
                .orElseThrow(() -> new IllegalArgumentException("운동 기록을 찾을 수 없습니다."));

        return WorkoutRecordDto.from(workoutRecord);

    }

    @Transactional(readOnly = true)
    public WorkoutSessionDto findWorkoutSession(User currentUser, Long workoutRecordId, Long workoutSessionId) {

        WorkoutSession workoutSession = workoutSessionRepository.findWithExercises(currentUser, workoutRecordId, workoutSessionId)
                .orElseThrow(() -> new IllegalIdentifierException("운동 세션을 찾을 수 없습니다."));

        return WorkoutSessionDto.from(workoutSession);
    }

    @Transactional(readOnly = true)
    public WorkoutExerciseDto findWorkoutExercise(User currentUser, Long workoutRecordId, Long workoutSessionId, Long workoutExerciseId) {

        WorkoutExercise workoutExercise = workoutExerciseRepository.findByRecordPath(currentUser, workoutRecordId, workoutSessionId, workoutExerciseId)
                .orElseThrow(() -> new IllegalArgumentException("운동 종목을 찾을 수 없습니다."));

        return WorkoutExerciseDto.from(workoutExercise);
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
