package example.repo;

import example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("select t from Teacher t left join fetch t.courses where t.phone = ?1")
    Optional<Teacher> findByPhone(String phone);

    @Override
    @Query("select distinct t from Teacher t left join fetch t.courses")
    @NonNull
    List<Teacher> findAll();
}
