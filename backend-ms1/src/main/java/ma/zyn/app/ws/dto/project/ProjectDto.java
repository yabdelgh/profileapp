package  ma.zyn.app.ws.dto.project;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.student.StudentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String startDate ;
    private String endDate ;
    private String status  ;

    private StudentDto student ;
    private ProjectStateDto projectState ;



    public ProjectDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }


    public StudentDto getStudent(){
        return this.student;
    }

    public void setStudent(StudentDto student){
        this.student = student;
    }
    public ProjectStateDto getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateDto projectState){
        this.projectState = projectState;
    }






}
