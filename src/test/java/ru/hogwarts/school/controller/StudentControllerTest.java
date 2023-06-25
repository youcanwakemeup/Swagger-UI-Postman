package ru.hogwarts.school.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.hogwarts.school.model.Student;

import ru.hogwarts.school.repository.StudentRepository;

import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void createStudentTest() throws Exception {
        Long id = 1L;
        String name = "Ivan";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("color", age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void getStudentTest() throws Exception {
        Long id = 1L;
        String name = "Ivan";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void getStudentByGreaterAgeTest() throws Exception {
        Long id = 1L;
        String name = "Ivan";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentRepository.findByAgeGreaterThan(any(int.class))).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/student", age)
                        .param("age", String.valueOf(age)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].age").value(age))
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    public void deleteStudentTest() throws Exception {
        long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", id))
                .andExpect(status().isOk());
        verify(studentService, times(1)).deleteStudent(id);
    }

    @Test
    public void editStudentTest() throws Exception {
        Long id = 1L;
        String name = "Ivan";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("color", age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        when(studentService.editStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void getStudentsInRangeTest() throws Exception {
        Long id = 1L;
        String name = "Ivan";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentRepository.findByAgeBetween(10, 30)).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/age")
                        .param("min", String.valueOf(10))
                        .param("max", String.valueOf(30)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(studentList.size()));
    }

}
