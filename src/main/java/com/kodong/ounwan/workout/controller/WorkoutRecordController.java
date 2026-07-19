package com.kodong.ounwan.workout.controller;


import com.kodong.ounwan.user.security.CustomUserDetails;
import com.kodong.ounwan.workout.dto.request.WorkoutRecordRequest;
import com.kodong.ounwan.workout.dto.response.WorkoutRecordResponse;
import com.kodong.ounwan.workout.service.WorkoutRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/workout-records")
public class WorkoutRecordController {

    private final WorkoutRecordService workoutRecordService;

    @PostMapping
    public ResponseEntity<WorkoutRecordResponse> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody WorkoutRecordRequest request
    ) {
        return ResponseEntity.ok(workoutRecordService.createWorkoutRecord(userDetails, request));
    }

    // 사용자가 한 전체 운동 조회
    @GetMapping
    public ResponseEntity<List<WorkoutRecordResponse>> findAll(
            @PathVariable Long userId // FIXME (@AuthenticationPrincipal 으로 교체)
    ) {
        return ResponseEntity.ok(
                workoutRecordService.findAllWorkoutRecord(userId)
        );
    }

    // 사용자가 지정한 날짜에 한 운동 조회
    @GetMapping
    public ResponseEntity<List<WorkoutRecordResponse>> findByDateRange(
            @PathVariable Long userId, // FIXME (@AuthenticationPrincipal 으로 교체)
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return ResponseEntity.ok(
                workoutRecordService.findWorkoutRecordByDateRange(userId, startDate, endDate)
        );
    }

    @PutMapping
    public ResponseEntity<WorkoutRecordResponse> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody WorkoutRecordRequest request
    ) {

    }

}