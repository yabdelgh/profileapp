package ma.zyn.app.dao.facade.core.project;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.Project;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjectDao extends AbstractRepository<Project,Long>  {

    List<Project> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Project> findByProjectStateCode(String code);
    List<Project> findByProjectStateId(Long id);
    int deleteByProjectStateId(Long id);
    int deleteByProjectStateCode(String code);
    long countByProjectStateCode(String code);


}
