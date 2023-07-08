package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Метод создания факультета");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Метод поиска факультета");

        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Метод изменения факультета");

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Метод удаления факультета");

        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findByColor(String color) {
        logger.info("Метод поиска факультетов по цвету");

        return facultyRepository.findByColorIsLikeIgnoreCase(color);
    }
    public Collection<Faculty> findByName(String name) {
        logger.info("Метод поиска факультетов по названию");

        return facultyRepository.findByNameLikeIgnoreCase(name);
    }

    public Faculty getFacultyOfStudent(Long id) {
        logger.info("Метод поиска факультета по ученику");

        return facultyRepository.findFacultyByStudentsId(id);
    }
    public String getLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
    public int streamAPI() {
        return Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
    }
}
