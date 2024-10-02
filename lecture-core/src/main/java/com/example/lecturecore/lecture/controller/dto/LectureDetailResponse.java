package com.example.lecturecore.lecture.controller.dto;

public record LectureDetailResponse (
        String tile,
        String professor,
        Integer remaining
) {
}
