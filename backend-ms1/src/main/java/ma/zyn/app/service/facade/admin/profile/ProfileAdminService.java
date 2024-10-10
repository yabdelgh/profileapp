package ma.zyn.app.service.facade.admin.profile;

import java.util.List;
import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.dao.criteria.core.profile.ProfileCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProfileAdminService {



    List<Profile> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);




	Profile create(Profile t);

    Profile update(Profile t);

    List<Profile> update(List<Profile> ts,boolean createIfNotExist);

    Profile findById(Long id);

    Profile findOrSave(Profile t);

    Profile findByReferenceEntity(Profile t);

    Profile findWithAssociatedLists(Long id);

    List<Profile> findAllOptimized();

    List<Profile> findAll();

    List<Profile> findByCriteria(ProfileCriteria criteria);

    List<Profile> findPaginatedByCriteria(ProfileCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProfileCriteria criteria);

    List<Profile> delete(List<Profile> ts);

    boolean deleteById(Long id);

    List<List<Profile>> getToBeSavedAndToBeDeleted(List<Profile> oldList, List<Profile> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
