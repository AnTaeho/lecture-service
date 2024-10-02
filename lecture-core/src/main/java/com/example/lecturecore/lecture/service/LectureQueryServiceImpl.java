package com.example.lecturecore.lecture.service;

import com.example.lecturecore.lecture.controller.dto.LectureDetailResponse;
import com.example.lecturecore.lecture.controller.dto.LectureListResponse;
import com.example.lecturecore.lecture.controller.dto.LectureSimpleResponse;
import com.example.lecturecore.lecture.domain.Lecture;
import com.example.lecturecore.lecture.repository.LectureRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService {

    private final LectureRepository lectureRepository;

    @Override
    public LectureDetailResponse getLectureDetail(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없다."));

        return new LectureDetailResponse(lecture.getTitle(), lecture.getProfessor(), lecture.getCapacity());
    }

    @Override
    public LectureListResponse getLectureList() {
        List<LectureSimpleResponse> result = lectureRepository.findAll().stream()
                .map(LectureSimpleResponse::of)
                .toList();
        return new LectureListResponse(result);
    }
}
