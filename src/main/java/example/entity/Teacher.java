package example.entity;

import example.dto.TeacherStatistics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQuery(name = "Teacher.calcTeacherStatistics",
        query = "select t.name, s1.avg_grade, s2.student_count " +
                "from (select ss2.t_id, avg(ss2.grade) as avg_grade " +
                "from (select ss.t_id, avg(ss.grade) as grade " +
                "from (select t.id as t_id, avg(ce.grade) as grade " +
                "from teacher t " +
                "left join teacher_courses tc on t.id = tc.teacher_id " +
                "left join course_enrollment ce on tc.course_id = ce.course_id " +
                "group by t.id, ce.student_id, ce.course_id) ss " +
                "group by ss.t_id) ss2 " +
                "group by t_id) s1 " +
                "join " +
                "(select t.id as t_id, count(sc.student_id) as student_count " +
                "from teacher t " +
                "left join teacher_courses tc on t.id = tc.teacher_id " +
                "left join student_courses sc on tc.course_id = sc.course_id " +
                "group by t.id) s2 " +
                "on s1.t_id = s2.t_id " +
                "join teacher t on s2.t_id = t.id",
        resultSetMapping = "Mapping.TeacherStatistics")
@SqlResultSetMapping(name = "Mapping.TeacherStatistics",
        classes = @ConstructorResult(targetClass = TeacherStatistics.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "avg_grade", type = Float.class),
                        @ColumnResult(name = "student_count", type = Long.class)
                }))
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "payment")
    private float payment;

    @ManyToMany
    @JoinTable(name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
