package  ma.zyn.app.dao.criteria.core.authentication;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class LoginCriteria extends  BaseCriteria  {

    private String email;
    private String emailLike;
    private String password;
    private String passwordLike;



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


}
