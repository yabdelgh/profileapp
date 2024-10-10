package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.ws.dto.student.StudentDto;

@Component
public class StudentConverter {



    public Student toItem(StudentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Student item = new Student();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public StudentDto toDto(Student item) {
        if (item == null) {
            return null;
        } else {
            StudentDto dto = new StudentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());


        return dto;
        }
    }


	
    public List<Student> toItem(List<StudentDto> dtos) {
        List<Student> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StudentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StudentDto> toDto(List<Student> items) {
        List<StudentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Student item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StudentDto dto, Student t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Student> copy(List<StudentDto> dtos) {
        List<Student> result = new ArrayList<>();
        if (dtos != null) {
            for (StudentDto dto : dtos) {
                Student instance = new Student();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
