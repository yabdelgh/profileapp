package ma.zyn.app.bean.core.project;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.student.Student;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_seq",sequenceName="project_seq",allocationSize=1, initialValue = 1)
public class Project  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    private String description;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    @Column(length = 500)
    private String status;

    private Student student ;
    private ProjectState projectState ;


    public Project(){
        super();
    }

    public Project(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    public Student getStudent(){
        return this.student;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_state")
    public ProjectState getProjectState(){
        return this.projectState;
    }
    public void setProjectState(ProjectState projectState){
        this.projectState = projectState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id != null && id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

