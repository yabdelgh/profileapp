package ma.zyn.app.unit.dao.facade.core.project;

import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.dao.facade.core.project.ProjectDao;

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

import ma.zyn.app.bean.core.project.ProjectState ;
import ma.zyn.app.bean.core.student.Student ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectDaoTest {

@Autowired
    private ProjectDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Project entity = new Project();
        entity.setId(id);
        underTest.save(entity);
        Project loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Project entity = new Project();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Project loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Project> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Project> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Project given = constructSample(1);
        Project saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Project constructSample(int i) {
		Project given = new Project();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setStatus("status-"+i);
        given.setStudent(new Student(1L));
        given.setProjectState(new ProjectState(1L));
        return given;
    }

}
