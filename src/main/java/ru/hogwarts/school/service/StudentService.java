package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;


import java.util.*;
import java.util.stream.Collectors;


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

    public Collection<String> getStudentsByLetter() {
        return studentRepository.findAll().stream()
                .parallel()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAgeStreamAPI() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }



    public void parallelThreads() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students.get(0));
        System.out.println(students.get(1));
        Thread firstThread = new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        });
        Thread secondThread = new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        });
        firstThread.start();
        secondThread.start();
    }

    public void syncThreads() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        Thread firstThread = new Thread(() -> {
            synchronized (StudentService.class) {
            printStudentInfoSync(students.get(2));
            printStudentInfoSync(students.get(3));
        }});
        Thread secondThread = new Thread(() -> {
            synchronized (StudentService.class) {
                printStudentInfoSync(students.get(4));
                printStudentInfoSync(students.get(5));
        }});
        firstThread.start();
        secondThread.start();
    }
    private synchronized void printStudentInfoSync(Student student) {
        System.out.println(studentRepository.findById(student.getId()));
    }
}



