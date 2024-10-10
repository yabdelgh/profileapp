package  ma.zyn.app.ws.dto.profile;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.authentication.LoginDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto  extends AuditBaseDto {

    private String firstName  ;
    private String lastName  ;
    private String phoneNumber  ;
    private String address  ;
    private String photo  ;

    private LoginDto user ;



    public ProfileDto(){
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


    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }


    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }


    public LoginDto getUser(){
        return this.user;
    }

    public void setUser(LoginDto user){
        this.user = user;
    }






}
