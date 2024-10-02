package com.example.lecturecore.lecture.repository;

import com.example.lecturecore.lecture.domain.UserLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
}
