package ma.zyn.app.unit.dao.facade.core.profile;

import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.dao.facade.core.profile.ProfileDao;

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

import ma.zyn.app.bean.core.authentication.Login ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProfileDaoTest {

@Autowired
    private ProfileDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Profile entity = new Profile();
        entity.setId(id);
        underTest.save(entity);
        Profile loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Profile entity = new Profile();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Profile loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Profile> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Profile> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Profile given = constructSample(1);
        Profile saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
