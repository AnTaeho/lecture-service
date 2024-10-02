package com.example.lecturecore.lecture.controller.dto;

import java.util.List;

public record LectureListResponse(
        List<LectureSimpleResponse> lectures
) {
}
