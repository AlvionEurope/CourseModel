package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CourseCompetitionRepositoryImpl implements CourseCompetitionRepository{

    private List<CourseCompletion> entities = new ArrayList<>();

    @Override
    public void create(CourseCompletion entity) {
        entities.add(entity);
    }

    @Override
    public void deleteBy(String studentName, String courseName) {
        List<CourseCompletion> current = findByStudentAndCourse(studentName, courseName);
        entities.remove(current);
    }

    @Override
    public List<CourseCompletion> findByStudentAndCourse(String studentName, String courseName) {
        List<CourseCompletion> current = entities
                .stream()
                .filter(competition -> competition.getStudentName()
                        .equals(studentName) && competition.getCourseName().equals(courseName)).collect(Collectors.toList());
//                .findFirst()
//                .get();

        return current;
    }

    @Override
    public List<CourseCompletion> findAllByStudent(String studentName) {
        List<CourseCompletion> current = entities
                .stream()
                .filter(competition -> competition.getStudentName() == studentName)
                .collect(Collectors.toList());

        return current;
    }
}
