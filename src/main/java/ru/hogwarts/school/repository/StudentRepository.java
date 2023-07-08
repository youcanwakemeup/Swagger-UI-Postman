package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import javax.management.openmbean.SimpleType;
import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>  {
    Collection<Student> findByAgeGreaterThan(int age);
    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findStudentByFacultyName(String name);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    int getAmountOfStudents();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getFiveLastStudents();

}
