package  ma.zyn.app.ws.converter.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.ws.dto.authentication.LoginDto;

@Component
public class LoginConverter {



    public Login toItem(LoginDto dto) {
        if (dto == null) {
            return null;
        } else {
        Login item = new Login();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());



        return item;
        }
    }


    public LoginDto toDto(Login item) {
        if (item == null) {
            return null;
        } else {
            LoginDto dto = new LoginDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());


        return dto;
        }
    }


	
    public List<Login> toItem(List<LoginDto> dtos) {
        List<Login> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LoginDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LoginDto> toDto(List<Login> items) {
        List<LoginDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Login item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LoginDto dto, Login t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Login> copy(List<LoginDto> dtos) {
        List<Login> result = new ArrayList<>();
        if (dtos != null) {
            for (LoginDto dto : dtos) {
                Login instance = new Login();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
