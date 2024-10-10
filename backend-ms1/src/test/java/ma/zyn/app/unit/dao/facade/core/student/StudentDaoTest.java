package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.dao.facade.core.student.StudentDao;

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
public class StudentDaoTest {

@Autowired
    private StudentDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Student entity = new Student();
        entity.setEmail(email);
        underTest.save(entity);
        Student loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-12345678";
        int result = underTest.deleteByEmail(email);

        Student loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Student entity = new Student();
        entity.setId(id);
        underTest.save(entity);
        Student loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Student entity = new Student();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Student loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Student> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Student> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Student given = constructSample(1);
        Student saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Student constructSample(int i) {
		Student given = new Student();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setUsername("username-"+i);
        given.setAccountNonExpired(false);
        given.setCredentialsNonExpired(false);
        given.setEnabled(false);
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        given.setAccountNonLocked(false);
        given.setPasswordChanged(false);
        return given;
    }

}
