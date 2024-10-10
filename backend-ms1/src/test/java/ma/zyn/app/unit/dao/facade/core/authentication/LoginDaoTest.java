package ma.zyn.app.unit.dao.facade.core.authentication;

import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.dao.facade.core.authentication.LoginDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LoginDaoTest {

@Autowired
    private LoginDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Login entity = new Login();
        entity.setEmail(email);
        underTest.save(entity);
        Login loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-12345678";
        int result = underTest.deleteByEmail(email);

        Login loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Login entity = new Login();
        entity.setId(id);
        underTest.save(entity);
        Login loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Login entity = new Login();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Login loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Login> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Login> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Login given = constructSample(1);
        Login saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Login constructSample(int i) {
		Login given = new Login();
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        return given;
    }

}
