package ma.zyn.app.unit.ws.facade.admin.profile;

import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.service.impl.admin.profile.ProfileAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.profile.ProfileRestAdmin;
import ma.zyn.app.ws.converter.profile.ProfileConverter;
import ma.zyn.app.ws.dto.profile.ProfileDto;
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
public class ProfileRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileAdminServiceImpl service;
    @Mock
    private ProfileConverter converter;

    @InjectMocks
    private ProfileRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProfileTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProfileDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProfileDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProfileTest() throws Exception {
        // Mock data
        ProfileDto requestDto = new ProfileDto();
        Profile entity = new Profile();
        Profile saved = new Profile();
        ProfileDto savedDto = new ProfileDto();

        // Mock the converter to return the profile object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved profile DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProfileDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProfileDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved profile DTO
        assertEquals(savedDto.getFirstName(), responseBody.getFirstName());
        assertEquals(savedDto.getLastName(), responseBody.getLastName());
        assertEquals(savedDto.getPhoneNumber(), responseBody.getPhoneNumber());
        assertEquals(savedDto.getAddress(), responseBody.getAddress());
        assertEquals(savedDto.getPhoto(), responseBody.getPhoto());
    }

}
