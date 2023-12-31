package ru.hogwarts.school.controller;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/age")
    public Collection<Student> findStudentsByAgeRange(@RequestParam("min") int minAge, @RequestParam("max") int maxAge) {
        return studentService.findInRangeAge(minAge, maxAge);
    }
    @GetMapping("/faculty")
    public Collection<Student> getStudentByFaculty(@RequestParam String facultyName) {
       return studentService.getStudentsInFaculty(facultyName);
    }
    @GetMapping("/total")
    public int getCountOfStudents() {
        return studentService.getStudentsCount();
    }
    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }
    @GetMapping("/last-students")
    public List<Student> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
    }

    @GetMapping("/students-letter")
    public Collection<String> getStudentsByLetterA() {
        return studentService.getStudentsByLetter();
    }

    @GetMapping("/students-average-age-stream")
    public double averageAge() {
        return studentService.getAverageAgeStreamAPI();
    }
}
