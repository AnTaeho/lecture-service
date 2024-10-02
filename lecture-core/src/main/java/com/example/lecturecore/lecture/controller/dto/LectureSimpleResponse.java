package com.example.lecturecore.lecture.controller.dto;

import com.example.lecturecore.lecture.domain.Lecture;

public record LectureSimpleResponse (
        String title,
        boolean isPossible
) {
    public static LectureSimpleResponse of(Lecture lecture) {
        return new LectureSimpleResponse(lecture.getTitle(), lecture.getCapacity() != 0);
    }
}
