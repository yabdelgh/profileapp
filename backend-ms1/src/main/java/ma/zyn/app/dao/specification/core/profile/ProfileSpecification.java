package  ma.zyn.app.dao.specification.core.profile;

import ma.zyn.app.dao.criteria.core.profile.ProfileCriteria;
import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProfileSpecification extends  AbstractSpecification<ProfileCriteria, Profile>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("address", criteria.getAddress(),criteria.getAddressLike());
        addPredicate("photo", criteria.getPhoto(),criteria.getPhotoLike());
        addPredicateFk("user","id", criteria.getUser()==null?null:criteria.getUser().getId());
        addPredicateFk("user","id", criteria.getUsers());
        addPredicateFk("user","email", criteria.getUser()==null?null:criteria.getUser().getEmail());
    }

    public ProfileSpecification(ProfileCriteria criteria) {
        super(criteria);
    }

    public ProfileSpecification(ProfileCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
