package me.rudnikov.backend.service;

public interface SubscriptionService {
    Boolean subscribeStudentToCourse(Long studentId, Long courseId);
    Boolean unsubscribeStudentFromCourse(Long studentId, Long courseId);
}