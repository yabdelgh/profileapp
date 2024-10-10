package ma.zyn.app.service.impl.admin.authentication;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.dao.criteria.core.authentication.LoginCriteria;
import ma.zyn.app.dao.facade.core.authentication.LoginDao;
import ma.zyn.app.dao.specification.core.authentication.LoginSpecification;
import ma.zyn.app.service.facade.admin.authentication.LoginAdminService;
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


import java.util.List;
@Service
public class LoginAdminServiceImpl implements LoginAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Login update(Login t) {
        Login loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Login.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Login findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Login findOrSave(Login t) {
        if (t != null) {
            Login result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Login> findAll() {
        return dao.findAll();
    }

    public List<Login> findByCriteria(LoginCriteria criteria) {
        List<Login> content = null;
        if (criteria != null) {
            LoginSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LoginSpecification constructSpecification(LoginCriteria criteria) {
        LoginSpecification mySpecification =  (LoginSpecification) RefelexivityUtil.constructObjectUsingOneParam(LoginSpecification.class, criteria);
        return mySpecification;
    }

    public List<Login> findPaginatedByCriteria(LoginCriteria criteria, int page, int pageSize, String order, String sortField) {
        LoginSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LoginCriteria criteria) {
        LoginSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<Login> delete(List<Login> list) {
		List<Login> result = new ArrayList();
        if (list != null) {
            for (Login t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Login create(Login t) {
        Login loaded = findByReferenceEntity(t);
        Login saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Login findWithAssociatedLists(Long id){
        Login result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Login> update(List<Login> ts, boolean createIfNotExist) {
        List<Login> result = new ArrayList<>();
        if (ts != null) {
            for (Login t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Login loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Login t, Login loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Login findByReferenceEntity(Login t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }



    public List<Login> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Login>> getToBeSavedAndToBeDeleted(List<Login> oldList, List<Login> newList) {
        List<List<Login>> result = new ArrayList<>();
        List<Login> resultDelete = new ArrayList<>();
        List<Login> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Login> oldList, List<Login> newList, List<Login> resultUpdateOrSave, List<Login> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Login myOld = oldList.get(i);
                Login t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Login myNew = newList.get(i);
                Login t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public LoginAdminServiceImpl(LoginDao dao) {
        this.dao = dao;
    }

    private LoginDao dao;
}
