package ru.wxw.coursemodel.wxw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.wxw.coursemodel.wxw.entity.Repotr;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Repotr, Long> {
    @Query(value = "SELECT t12.id1 as id, t12.name as name_professor, t12.count as sum_student_all_course, t2.avg as average_stage_all_student from\n" +
            "    (SELECT professor.name, avg(c2.grade) as avg FROM professor\n" +
            "                                                          JOIN course c on professor.id = c.professor_id\n" +
            "                                                          JOIN coursestage c2 on c.id = c2.course_id\n" +
            "     group by professor.name) as t2\n" +
            "        left join\n" +
            "    (SELECT professor.id as id1, professor.name,  count(c3.course_id) as count FROM professor\n" +
            "                                                                                        JOIN course c on professor.id = c.professor_id\n" +
            "                                                                                        join coursestudent c3 on c.id = c3.course_id\n" +
            "\n" +
            "     group by professor.name, id1) as t12 on t12.name = t2.name", nativeQuery = true)
    List<Repotr> getProfAndSumStudentAndStudentAverage();
}
