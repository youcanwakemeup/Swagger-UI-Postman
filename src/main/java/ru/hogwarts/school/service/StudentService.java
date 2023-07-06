package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        logger.info("Метод добавления студента");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Метод поиска студента");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Метод изменения студента");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Метод удаления студента");
        studentRepository.deleteById(id);
    }
    public Collection<Student> findByAge(int age) {
        logger.info("Метод поиска студентов по возрасту (больше определенного)");
        return studentRepository.findByAgeGreaterThan(age);
    }
    public Collection<Student> findInRangeAge(int min, int max) {
        logger.info("Метод поиск студентов в диапазоне возрастов");
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> getStudentsInFaculty(String name) {
        logger.info("Метод поиска студентов в факультете");
        return studentRepository.findStudentByFacultyName(name);
    }
    public int getStudentsCount() {
        logger.info("Метод подсчетов студентов");
        return studentRepository.getAmountOfStudents();
    }

    public double getAverageAge() {
        logger.info("Метод среднего возраста студентов");
        return studentRepository.getAverageAge();
    }
    public List<Student> getFiveLastStudents() {
        logger.info("Метод поиска последних пяти студентов");
        return studentRepository.getFiveLastStudents();
    }
}



