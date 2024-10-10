package ma.zyn.app.service.facade.admin.authentication;

import java.util.List;
import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.dao.criteria.core.authentication.LoginCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface LoginAdminService {







	Login create(Login t);

    Login update(Login t);

    List<Login> update(List<Login> ts,boolean createIfNotExist);

    Login findById(Long id);

    Login findOrSave(Login t);

    Login findByReferenceEntity(Login t);

    Login findWithAssociatedLists(Long id);

    List<Login> findAllOptimized();

    List<Login> findAll();

    List<Login> findByCriteria(LoginCriteria criteria);

    List<Login> findPaginatedByCriteria(LoginCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LoginCriteria criteria);

    List<Login> delete(List<Login> ts);

    boolean deleteById(Long id);

    List<List<Login>> getToBeSavedAndToBeDeleted(List<Login> oldList, List<Login> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
