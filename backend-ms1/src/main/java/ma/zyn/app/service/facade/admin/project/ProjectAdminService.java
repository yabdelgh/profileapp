package ma.zyn.app.service.facade.admin.project;

import java.util.List;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.dao.criteria.core.project.ProjectCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProjectAdminService {



    List<Project> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Project> findByProjectStateCode(String code);
    List<Project> findByProjectStateId(Long id);
    int deleteByProjectStateId(Long id);
    int deleteByProjectStateCode(String code);
    long countByProjectStateCode(String code);




	Project create(Project t);

    Project update(Project t);

    List<Project> update(List<Project> ts,boolean createIfNotExist);

    Project findById(Long id);

    Project findOrSave(Project t);

    Project findByReferenceEntity(Project t);

    Project findWithAssociatedLists(Long id);

    List<Project> findAllOptimized();

    List<Project> findAll();

    List<Project> findByCriteria(ProjectCriteria criteria);

    List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectCriteria criteria);

    List<Project> delete(List<Project> ts);

    boolean deleteById(Long id);

    List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
