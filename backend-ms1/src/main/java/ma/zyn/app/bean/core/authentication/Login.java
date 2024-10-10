package ma.zyn.app.bean.core.authentication;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="login_seq",sequenceName="login_seq",allocationSize=1, initialValue = 1)
public class Login  extends BaseEntity     {




    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String password;



    public Login(){
        super();
    }

    public Login(Long id){
        this.id = id;
    }

    public Login(Long id,String email){
        this.id = id;
        this.email = email ;
    }
    public Login(String email){
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="login_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return id != null && id.equals(login.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

