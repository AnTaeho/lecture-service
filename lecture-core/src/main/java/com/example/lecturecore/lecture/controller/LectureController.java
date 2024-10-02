package com.example.lecturecore.lecture.controller;

import com.example.lecturecore.common.dto.CommonResponse;
import com.example.lecturecore.lecture.controller.dto.LectureDetailResponse;
import com.example.lecturecore.lecture.controller.dto.LectureListResponse;
import com.example.lecturecore.lecture.controller.dto.LectureRequest;
import com.example.lecturecore.lecture.controller.dto.LectureResponse;
import com.example.lecturecore.lecture.service.LectureCommandService;
import com.example.lecturecore.lecture.service.LectureQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureCommandService lectureCommandService;
    private final LectureQueryService lectureQueryService;

    @PostMapping
    public CommonResponse<LectureResponse> createLecture(@RequestBody LectureRequest lectureRequest) {
        return new CommonResponse<>(lectureCommandService.createLecture(lectureRequest));
    }

    @PostMapping("/{lectureId}")
    public CommonResponse<LectureResponse> registerLecture(@PathVariable("lectureId") Long lectureId) {
        return new CommonResponse<>(lectureCommandService.registerLecture(getUserName(), lectureId));
    }

    @GetMapping("/{lectureId}")
    public CommonResponse<LectureDetailResponse> getLectureDetail(@PathVariable("lectureId") Long lectureId) {
        return new CommonResponse<>(lectureQueryService.getLectureDetail(lectureId));
    }

    @GetMapping
    public CommonResponse<LectureListResponse> getAllLectures() {
        return new CommonResponse<>(lectureQueryService.getLectureList());
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
