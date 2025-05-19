package com.example.studentmarks;

import com.example.studentmarks.controller.StudentController;
import com.example.studentmarks.model.Student;
import com.example.studentmarks.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testShowForm() throws Exception {
        mockMvc.perform(get("/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void testSubmitForm() throws Exception {
        Mockito.when(studentService.saveStudent(any(Student.class)))
                .thenReturn(new Student("Test", 80, 80, 80, 80));

        mockMvc.perform(post("/submit")
                .param("name", "Test")
                .param("dataScience", "80")
                .param("maths", "80")
                .param("python", "80")
                .param("dataStructures", "80"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/report"));
    }

    @Test
    void testShowReport() throws Exception {
        Mockito.when(studentService.getAllStudents())
                .thenReturn(Arrays.asList(
                        new Student("Alice", 90, 85, 95, 80),
                        new Student("Bob", 70, 75, 80, 85)
                ));

        mockMvc.perform(get("/report"))
                .andExpect(status().isOk())
                .andExpect(view().name("report"))
                .andExpect(model().attributeExists("students"));
    }
}
