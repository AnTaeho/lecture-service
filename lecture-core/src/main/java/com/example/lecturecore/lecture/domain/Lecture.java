package com.example.lecturecore.lecture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    private String title;
    private String professor;

    private Integer capacity;

    @OneToMany(mappedBy = "lecture")
    private final List<UserLecture> userLectures = new ArrayList<>();

    public Lecture(String title, String professor, Integer capacity) {
        this.title = title;
        this.professor = professor;
        this.capacity = capacity;
    }

    public void add(UserLecture userLecture) {
        this.userLectures.add(userLecture);
    }

    public void register() {
        this.capacity -= 1;
    }
}
