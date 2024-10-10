package  ma.zyn.app.dao.criteria.core.project;


import ma.zyn.app.dao.criteria.core.student.StudentCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ProjectCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private String status;
    private String statusLike;

    private StudentCriteria student ;
    private List<StudentCriteria> students ;
    private ProjectStateCriteria projectState ;
    private List<ProjectStateCriteria> projectStates ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatusLike(){
        return this.statusLike;
    }
    public void setStatusLike(String statusLike){
        this.statusLike = statusLike;
    }


    public StudentCriteria getStudent(){
        return this.student;
    }

    public void setStudent(StudentCriteria student){
        this.student = student;
    }
    public List<StudentCriteria> getStudents(){
        return this.students;
    }

    public void setStudents(List<StudentCriteria> students){
        this.students = students;
    }
    public ProjectStateCriteria getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateCriteria projectState){
        this.projectState = projectState;
    }
    public List<ProjectStateCriteria> getProjectStates(){
        return this.projectStates;
    }

    public void setProjectStates(List<ProjectStateCriteria> projectStates){
        this.projectStates = projectStates;
    }
}
