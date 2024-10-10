package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.StudentCriteria;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StudentSpecification extends  AbstractSpecification<StudentCriteria, Student>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
    }

    public StudentSpecification(StudentCriteria criteria) {
        super(criteria);
    }

    public StudentSpecification(StudentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
