package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.service.impl.admin.student.StudentAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.StudentRestAdmin;
import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.ws.dto.student.StudentDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StudentAdminServiceImpl service;
    @Mock
    private StudentConverter converter;

    @InjectMocks
    private StudentRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStudentTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StudentDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StudentDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStudentTest() throws Exception {
        // Mock data
        StudentDto requestDto = new StudentDto();
        Student entity = new Student();
        Student saved = new Student();
        StudentDto savedDto = new StudentDto();

        // Mock the converter to return the student object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved student DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StudentDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StudentDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved student DTO
        assertEquals(savedDto.getFirstName(), responseBody.getFirstName());
        assertEquals(savedDto.getLastName(), responseBody.getLastName());
        assertEquals(savedDto.getPhoneNumber(), responseBody.getPhoneNumber());
        assertEquals(savedDto.getUsername(), responseBody.getUsername());
        assertEquals(savedDto.getAccountNonExpired(), responseBody.getAccountNonExpired());
        assertEquals(savedDto.getCredentialsNonExpired(), responseBody.getCredentialsNonExpired());
        assertEquals(savedDto.getEnabled(), responseBody.getEnabled());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
        assertEquals(savedDto.getAccountNonLocked(), responseBody.getAccountNonLocked());
        assertEquals(savedDto.getPasswordChanged(), responseBody.getPasswordChanged());
    }

}
