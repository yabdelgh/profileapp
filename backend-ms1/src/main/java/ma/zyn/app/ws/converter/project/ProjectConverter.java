package  ma.zyn.app.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.project.ProjectStateConverter;
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.bean.core.student.Student;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.ws.dto.project.ProjectDto;

@Component
public class ProjectConverter {

    @Autowired
    private ProjectStateConverter projectStateConverter ;
    @Autowired
    private StudentConverter studentConverter ;
    private boolean student;
    private boolean projectState;

    public  ProjectConverter() {
        initObject(true);
    }

    public Project toItem(ProjectDto dto) {
        if (dto == null) {
            return null;
        } else {
        Project item = new Project();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getStatus()))
                item.setStatus(dto.getStatus());
            if(this.student && dto.getStudent()!=null)
                item.setStudent(studentConverter.toItem(dto.getStudent())) ;

            if(this.projectState && dto.getProjectState()!=null)
                item.setProjectState(projectStateConverter.toItem(dto.getProjectState())) ;




        return item;
        }
    }


    public ProjectDto toDto(Project item) {
        if (item == null) {
            return null;
        } else {
            ProjectDto dto = new ProjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(StringUtil.isNotEmpty(item.getStatus()))
                dto.setStatus(item.getStatus());
            if(this.student && item.getStudent()!=null) {
                dto.setStudent(studentConverter.toDto(item.getStudent())) ;

            }
            if(this.projectState && item.getProjectState()!=null) {
                dto.setProjectState(projectStateConverter.toDto(item.getProjectState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.student = value;
        this.projectState = value;
    }
	
    public List<Project> toItem(List<ProjectDto> dtos) {
        List<Project> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectDto> toDto(List<Project> items) {
        List<ProjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Project item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectDto dto, Project t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStudent() == null  && dto.getStudent() != null){
            t.setStudent(new Student());
        }else if (t.getStudent() != null  && dto.getStudent() != null){
            t.setStudent(null);
            t.setStudent(new Student());
        }
        if(t.getProjectState() == null  && dto.getProjectState() != null){
            t.setProjectState(new ProjectState());
        }else if (t.getProjectState() != null  && dto.getProjectState() != null){
            t.setProjectState(null);
            t.setProjectState(new ProjectState());
        }
        if (dto.getStudent() != null)
        studentConverter.copy(dto.getStudent(), t.getStudent());
        if (dto.getProjectState() != null)
        projectStateConverter.copy(dto.getProjectState(), t.getProjectState());
    }

    public List<Project> copy(List<ProjectDto> dtos) {
        List<Project> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectDto dto : dtos) {
                Project instance = new Project();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectStateConverter getProjectStateConverter(){
        return this.projectStateConverter;
    }
    public void setProjectStateConverter(ProjectStateConverter projectStateConverter ){
        this.projectStateConverter = projectStateConverter;
    }
    public StudentConverter getStudentConverter(){
        return this.studentConverter;
    }
    public void setStudentConverter(StudentConverter studentConverter ){
        this.studentConverter = studentConverter;
    }
    public boolean  isStudent(){
        return this.student;
    }
    public void  setStudent(boolean student){
        this.student = student;
    }
    public boolean  isProjectState(){
        return this.projectState;
    }
    public void  setProjectState(boolean projectState){
        this.projectState = projectState;
    }
}
