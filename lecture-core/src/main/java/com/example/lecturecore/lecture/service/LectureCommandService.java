package com.example.lecturecore.lecture.service;

import com.example.lecturecore.lecture.controller.dto.LectureRequest;
import com.example.lecturecore.lecture.controller.dto.LectureResponse;

public interface LectureCommandService {

    LectureResponse createLecture(LectureRequest lectureRequest);
    LectureResponse registerLecture(String email, Long lectureId);

}
