package ma.zyn.app.unit.service.impl.admin.authentication;

import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.dao.facade.core.authentication.LoginDao;
import ma.zyn.app.service.impl.admin.authentication.LoginAdminServiceImpl;

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
class LoginAdminServiceImplTest {

    @Mock
    private LoginDao repository;
    private AutoCloseable autoCloseable;
    private LoginAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LoginAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllLogin() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveLogin() {
        // Given
        Login toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteLogin() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetLoginById() {
        // Given
        Long idToRetrieve = 1L; // Example Login ID to retrieve
        Login expected = new Login(); // You need to replace Login with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Login result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Login constructSample(int i) {
		Login given = new Login();
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        return given;
    }

}
