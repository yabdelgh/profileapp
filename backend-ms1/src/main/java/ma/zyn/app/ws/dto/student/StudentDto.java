package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto  extends UserDto {

    private String firstName  ;
    private String lastName  ;
    private String phoneNumber  ;




    private Collection<Role> roles;
    public StudentDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

















    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
