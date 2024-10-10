package ma.zyn.app.service.facade.admin.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.dao.criteria.core.student.StudentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface StudentAdminService {


    Student findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Student create(Student t);

    Student update(Student t);

    List<Student> update(List<Student> ts,boolean createIfNotExist);

    Student findById(Long id);

    Student findOrSave(Student t);

    Student findByReferenceEntity(Student t);

    Student findWithAssociatedLists(Long id);

    List<Student> findAllOptimized();

    List<Student> findAll();

    List<Student> findByCriteria(StudentCriteria criteria);

    List<Student> findPaginatedByCriteria(StudentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StudentCriteria criteria);

    List<Student> delete(List<Student> ts);

    boolean deleteById(Long id);

    List<List<Student>> getToBeSavedAndToBeDeleted(List<Student> oldList, List<Student> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
