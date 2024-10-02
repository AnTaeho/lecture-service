package com.example.lecturecore.lecture.controller.dto;

public record LectureRequest(
        String title,
        String professor,
        Integer capacity
) {
}
