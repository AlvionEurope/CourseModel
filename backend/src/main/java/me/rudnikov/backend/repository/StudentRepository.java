package me.rudnikov.backend.repository;

import me.rudnikov.backend.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByPhoneNumberOrRecordBook(
            String phoneNumber,
            Integer recordBook
    );

    List<Student> findAllByFullName(String fullName, Pageable pageable);

    List<Student> findAllByAveragePerformance(Float averagePerformance, Pageable pageable);
}