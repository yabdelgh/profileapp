package ma.zyn.app.unit.service.impl.admin.profile;

import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.dao.facade.core.profile.ProfileDao;
import ma.zyn.app.service.impl.admin.profile.ProfileAdminServiceImpl;

import ma.zyn.app.bean.core.authentication.Login ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProfileAdminServiceImplTest {

    @Mock
    private ProfileDao repository;
    private AutoCloseable autoCloseable;
    private ProfileAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProfileAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProfile() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProfile() {
        // Given
        Profile toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProfile() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProfileById() {
        // Given
        Long idToRetrieve = 1L; // Example Profile ID to retrieve
        Profile expected = new Profile(); // You need to replace Profile with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Profile result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Profile constructSample(int i) {
		Profile given = new Profile();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setAddress("address-"+i);
        given.setPhoto("photo-"+i);
        given.setUser(new Login(1L));
        return given;
    }

}
