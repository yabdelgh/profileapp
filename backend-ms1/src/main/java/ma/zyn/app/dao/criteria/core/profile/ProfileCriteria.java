package  ma.zyn.app.dao.criteria.core.profile;


import ma.zyn.app.dao.criteria.core.authentication.LoginCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProfileCriteria extends  BaseCriteria  {

    private String firstName;
    private String firstNameLike;
    private String lastName;
    private String lastNameLike;
    private String phoneNumber;
    private String phoneNumberLike;
    private String address;
    private String addressLike;
    private String photo;
    private String photoLike;

    private LoginCriteria user ;
    private List<LoginCriteria> users ;


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

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddressLike(){
        return this.addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }

    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public String getPhotoLike(){
        return this.photoLike;
    }
    public void setPhotoLike(String photoLike){
        this.photoLike = photoLike;
    }


    public LoginCriteria getUser(){
        return this.user;
    }

    public void setUser(LoginCriteria user){
        this.user = user;
    }
    public List<LoginCriteria> getUsers(){
        return this.users;
    }

    public void setUsers(List<LoginCriteria> users){
        this.users = users;
    }
}
