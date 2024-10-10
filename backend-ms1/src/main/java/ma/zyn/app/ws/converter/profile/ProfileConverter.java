package  ma.zyn.app.ws.converter.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.authentication.LoginConverter;
import ma.zyn.app.bean.core.authentication.Login;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.ws.dto.profile.ProfileDto;

@Component
public class ProfileConverter {

    @Autowired
    private LoginConverter loginConverter ;
    private boolean user;

    public  ProfileConverter() {
        initObject(true);
    }

    public Profile toItem(ProfileDto dto) {
        if (dto == null) {
            return null;
        } else {
        Profile item = new Profile();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getAddress()))
                item.setAddress(dto.getAddress());
            if(StringUtil.isNotEmpty(dto.getPhoto()))
                item.setPhoto(dto.getPhoto());
            if(this.user && dto.getUser()!=null)
                item.setUser(loginConverter.toItem(dto.getUser())) ;




        return item;
        }
    }


    public ProfileDto toDto(Profile item) {
        if (item == null) {
            return null;
        } else {
            ProfileDto dto = new ProfileDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getAddress()))
                dto.setAddress(item.getAddress());
            if(StringUtil.isNotEmpty(item.getPhoto()))
                dto.setPhoto(item.getPhoto());
            if(this.user && item.getUser()!=null) {
                dto.setUser(loginConverter.toDto(item.getUser())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.user = value;
    }
	
    public List<Profile> toItem(List<ProfileDto> dtos) {
        List<Profile> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProfileDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProfileDto> toDto(List<Profile> items) {
        List<ProfileDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Profile item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProfileDto dto, Profile t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getUser() == null  && dto.getUser() != null){
            t.setUser(new Login());
        }else if (t.getUser() != null  && dto.getUser() != null){
            t.setUser(null);
            t.setUser(new Login());
        }
        if (dto.getUser() != null)
        loginConverter.copy(dto.getUser(), t.getUser());
    }

    public List<Profile> copy(List<ProfileDto> dtos) {
        List<Profile> result = new ArrayList<>();
        if (dtos != null) {
            for (ProfileDto dto : dtos) {
                Profile instance = new Profile();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public LoginConverter getLoginConverter(){
        return this.loginConverter;
    }
    public void setLoginConverter(LoginConverter loginConverter ){
        this.loginConverter = loginConverter;
    }
    public boolean  isUser(){
        return this.user;
    }
    public void  setUser(boolean user){
        this.user = user;
    }
}
