package ma.zyn.app.service.impl.admin.profile;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.dao.criteria.core.profile.ProfileCriteria;
import ma.zyn.app.dao.facade.core.profile.ProfileDao;
import ma.zyn.app.dao.specification.core.profile.ProfileSpecification;
import ma.zyn.app.service.facade.admin.profile.ProfileAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.authentication.LoginAdminService ;
import ma.zyn.app.bean.core.authentication.Login ;

import java.util.List;
@Service
public class ProfileAdminServiceImpl implements ProfileAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Profile update(Profile t) {
        Profile loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Profile.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Profile findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Profile findOrSave(Profile t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Profile result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Profile> findAll() {
        return dao.findAll();
    }

    public List<Profile> findByCriteria(ProfileCriteria criteria) {
        List<Profile> content = null;
        if (criteria != null) {
            ProfileSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ProfileSpecification constructSpecification(ProfileCriteria criteria) {
        ProfileSpecification mySpecification =  (ProfileSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProfileSpecification.class, criteria);
        return mySpecification;
    }

    public List<Profile> findPaginatedByCriteria(ProfileCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProfileSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProfileCriteria criteria) {
        ProfileSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Profile> findByUserId(Long id){
        return dao.findByUserId(id);
    }
    public int deleteByUserId(Long id){
        return dao.deleteByUserId(id);
    }
    public long countByUserEmail(String email){
        return dao.countByUserEmail(email);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Profile> delete(List<Profile> list) {
		List<Profile> result = new ArrayList();
        if (list != null) {
            for (Profile t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Profile create(Profile t) {
        Profile loaded = findByReferenceEntity(t);
        Profile saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Profile findWithAssociatedLists(Long id){
        Profile result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Profile> update(List<Profile> ts, boolean createIfNotExist) {
        List<Profile> result = new ArrayList<>();
        if (ts != null) {
            for (Profile t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Profile loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Profile t, Profile loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Profile findByReferenceEntity(Profile t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Profile t){
        if( t != null) {
            t.setUser(loginService.findOrSave(t.getUser()));
        }
    }



    public List<Profile> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Profile>> getToBeSavedAndToBeDeleted(List<Profile> oldList, List<Profile> newList) {
        List<List<Profile>> result = new ArrayList<>();
        List<Profile> resultDelete = new ArrayList<>();
        List<Profile> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Profile> oldList, List<Profile> newList, List<Profile> resultUpdateOrSave, List<Profile> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Profile myOld = oldList.get(i);
                Profile t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Profile myNew = newList.get(i);
                Profile t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private LoginAdminService loginService ;

    public ProfileAdminServiceImpl(ProfileDao dao) {
        this.dao = dao;
    }

    private ProfileDao dao;
}
