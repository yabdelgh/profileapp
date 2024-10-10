package ma.zyn.app.dao.facade.core.student;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.Student;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.student.Student;
import java.util.List;


@Repository
public interface StudentDao extends AbstractRepository<Student,Long>  {
    Student findByEmail(String email);
    int deleteByEmail(String email);

    Student findByUsername(String username);

    @Query("SELECT NEW Student(item.id,item.email) FROM Student item")
    List<Student> findAllOptimized();

}
