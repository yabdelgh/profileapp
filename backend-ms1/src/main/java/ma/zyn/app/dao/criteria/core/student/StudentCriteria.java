package  ma.zyn.app.dao.criteria.core.student;



import ma.zyn.app.zynerator.security.dao.criteria.core.UserCriteria;

import java.util.List;

public class StudentCriteria extends UserCriteria  {

    private String firstName;
    private String firstNameLike;
    private String lastName;
    private String lastNameLike;
    private String phoneNumber;
    private String phoneNumberLike;
    private String username;
    private String usernameLike;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private String email;
    private String emailLike;
    private String password;
    private String passwordLike;
    private Boolean accountNonLocked;
    private Boolean passwordChanged;



    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstNameLike(){
        return this.firstNameLike;
    }
    public void setFirstNameLike(String firstNameLike){
        this.firstNameLike = firstNameLike;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastNameLike(){
        return this.lastNameLike;
    }
    public void setLastNameLike(String lastNameLike){
        this.lastNameLike = lastNameLike;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumberLike(){
        return this.phoneNumberLike;
    }
    public void setPhoneNumberLike(String phoneNumberLike){
        this.phoneNumberLike = phoneNumberLike;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }

    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }

}
