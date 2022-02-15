package example.repo;

import example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByEmail(String email);

    @Override
    @Query("select distinct s from Student s left join fetch s.courses")
    @NonNull
    List<Student> findAll();
}
