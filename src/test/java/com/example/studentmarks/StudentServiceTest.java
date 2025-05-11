package com.example.studentmarks;

import com.example.studentmarks.model.Student;
import com.example.studentmarks.repository.StudentRepository;
import com.example.studentmarks.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student("Alice", 90, 85, 95, 80);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student saved = studentService.saveStudent(student);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        assertEquals("Alice", captor.getValue().getName());
        assertEquals(90, captor.getValue().getDataScience());
        assertEquals(85, captor.getValue().getMaths());
        assertEquals(95, captor.getValue().getPython());
        assertEquals(80, captor.getValue().getDataStructures());
        assertEquals(student, saved);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(
                new Student("Bob", 70, 75, 80, 85),
                new Student("Carol", 88, 92, 85, 90)
        );
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        assertEquals("Bob", result.get(0).getName());
        assertEquals("Carol", result.get(1).getName());
    }
}
