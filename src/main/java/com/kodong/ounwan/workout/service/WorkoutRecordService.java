package com.kodong.ounwan.workout.service;

import com.kodong.ounwan.user.entity.User;
import com.kodong.ounwan.user.repository.UserRepository;
import com.kodong.ounwan.user.security.CustomUserDetails;
import com.kodong.ounwan.workout.dto.request.ExerciseRequest;
import com.kodong.ounwan.workout.dto.request.WorkoutRecordRequest;
import com.kodong.ounwan.workout.dto.response.WorkoutRecordResponse;
import com.kodong.ounwan.workout.entity.ExerciseRecord;
import com.kodong.ounwan.workout.entity.ExerciseSet;
import com.kodong.ounwan.workout.entity.WorkoutRecord;
import com.kodong.ounwan.workout.repository.ExerciseRecordRepository;
import com.kodong.ounwan.workout.repository.ExerciseSetRepository;
import com.kodong.ounwan.workout.repository.WorkoutRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final UserRepository userRepository;
    private final WorkoutRecordRepository workoutRecordRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseSetRepository exerciseSetRepository;

    @Transactional
    public WorkoutRecordResponse createWorkoutRecord(
            CustomUserDetails userDetails,
            WorkoutRecordRequest request
    ) {
        // 1. 운동 기록을 작성하는 사용자 조회
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "사용자를 찾을 수 없습니다."
                        )
                );

        // 2. 하루 운동 기록 생성 및 저장
        WorkoutRecord workoutRecord = new WorkoutRecord(
                user,
                request.getWorkoutDate(),
                request.getMemo()
        );

        workoutRecordRepository.save(workoutRecord);

        // 3. 운동 종목들을 하나씩 저장
        for (ExerciseRequest exerciseRequest : request.getExercises()) {

            ExerciseRecord exerciseRecord =
                    new ExerciseRecord(
                            workoutRecord,
                            exerciseRequest.getExerciseType()
                    );

            exerciseRecordRepository.save(exerciseRecord);

            // 4. 해당 운동의 세트들을 저장
            List<ExerciseSet> exerciseSets = exerciseRequest.getSets().stream()
                            .map(setRequest ->
                                    new ExerciseSet(
                                            exerciseRecord,
                                            setRequest.getSetNumber(),
                                            setRequest.getWeight(),
                                            setRequest.getReps(),
                                            setRequest.getDescription()
                                    )
                            )
                            .toList();

            exerciseSetRepository.saveAll(exerciseSets);
        }

        return WorkoutRecordResponse.from(workoutRecord); // FIXME (WorkoutRecord 내에 있는 User, OneToMany관계(ExerciseRecord, ExerciseSet)가 반영이 될까..?)
    }

    @Transactional(readOnly = true)
    public List<WorkoutRecordResponse> findAllWorkoutRecord(Long userId) {
        List<WorkoutRecord> workoutRecords = workoutRecordRepository.findAllById(userId);

        return workoutRecords.stream()
                .map(workoutRecord -> WorkoutRecordResponse.from(workoutRecord))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<WorkoutRecordResponse> findWorkoutRecordByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {

        List<WorkoutRecord> workoutRecords
                = workoutRecordRepository.findWorkoutRecordByUser_IdAndWorkoutDateBetweenOrderByWorkoutDateDesc(userId, startDate, endDate);

        return workoutRecords.stream()
                .map(workoutRecord -> WorkoutRecordResponse.from(workoutRecord))
                .toList();
    }


}