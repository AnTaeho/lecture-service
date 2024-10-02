package com.example.lecturecore.lecture.service;

import com.example.lecturecore.lecture.controller.dto.LectureRequest;
import com.example.lecturecore.lecture.controller.dto.LectureResponse;
import com.example.lecturecore.lecture.domain.Lecture;
import com.example.lecturecore.lecture.domain.UserLecture;
import com.example.lecturecore.lecture.repository.LectureRepository;
import com.example.lecturecore.lecture.repository.UserLectureRepository;
import com.example.lecturecore.user.domain.User;
import com.example.lecturecore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl implements LectureCommandService {

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;

    @Override
    public LectureResponse createLecture(LectureRequest lectureRequest) {
        Lecture lecture = new Lecture(
                lectureRequest.title(),
                lectureRequest.professor(),
                lectureRequest.capacity()
        );

        Lecture savedLecture = lectureRepository.save(lecture);
        return new LectureResponse(savedLecture.getId());
    }

    @Override
    public LectureResponse registerLecture(String email, Long lectureId) {
        User user = getUser(email);
        Lecture lecture = getLecture(lectureId);

        lecture.register();
        UserLecture userLecture = new UserLecture(user, lecture);
        userLectureRepository.save(userLecture);
        return new LectureResponse(lectureId);
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저 찾을 수 없다."));
    }

    private Lecture getLecture(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의 찾을 수 없다."));
    }
}
