package  ma.zyn.app.ws.dto.authentication;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto  extends AuditBaseDto {

    private String email  ;
    private String password  ;




    public LoginDto(){
        super();
    }




    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }








}
