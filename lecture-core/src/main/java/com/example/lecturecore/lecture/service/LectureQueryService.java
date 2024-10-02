package com.example.lecturecore.lecture.service;

import com.example.lecturecore.lecture.controller.dto.LectureDetailResponse;
import com.example.lecturecore.lecture.controller.dto.LectureListResponse;

public interface LectureQueryService {

    LectureDetailResponse getLectureDetail(Long lectureId);
    LectureListResponse getLectureList();

}
