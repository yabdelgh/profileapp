package  ma.zyn.app.dao.specification.core.authentication;

import ma.zyn.app.dao.criteria.core.authentication.LoginCriteria;
import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LoginSpecification extends  AbstractSpecification<LoginCriteria, Login>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public LoginSpecification(LoginCriteria criteria) {
        super(criteria);
    }

    public LoginSpecification(LoginCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
