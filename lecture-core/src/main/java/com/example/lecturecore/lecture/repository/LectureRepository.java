package com.example.lecturecore.lecture.repository;

import com.example.lecturecore.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
