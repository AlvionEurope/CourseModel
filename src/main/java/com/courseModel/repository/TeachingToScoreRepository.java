package com.courseModel.repository;

import com.courseModel.entity.TeachingToScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachingToScoreRepository extends JpaRepository<TeachingToScore, Integer> {
    Optional<TeachingToScore> findByTeachingId(int teaching);
}
