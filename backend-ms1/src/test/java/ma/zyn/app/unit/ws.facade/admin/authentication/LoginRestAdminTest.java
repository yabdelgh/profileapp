package ma.zyn.app.unit.ws.facade.admin.authentication;

import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.service.impl.admin.authentication.LoginAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.authentication.LoginRestAdmin;
import ma.zyn.app.ws.converter.authentication.LoginConverter;
import ma.zyn.app.ws.dto.authentication.LoginDto;
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
public class LoginRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private LoginAdminServiceImpl service;
    @Mock
    private LoginConverter converter;

    @InjectMocks
    private LoginRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllLoginTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<LoginDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<LoginDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveLoginTest() throws Exception {
        // Mock data
        LoginDto requestDto = new LoginDto();
        Login entity = new Login();
        Login saved = new Login();
        LoginDto savedDto = new LoginDto();

        // Mock the converter to return the login object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved login DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<LoginDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        LoginDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved login DTO
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
    }

}
