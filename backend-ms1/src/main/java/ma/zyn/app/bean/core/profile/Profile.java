package ma.zyn.app.bean.core.profile;






import ma.zyn.app.bean.core.authentication.Login;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="profile_seq",sequenceName="profile_seq",allocationSize=1, initialValue = 1)
public class Profile  extends BaseEntity     {




    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String phoneNumber;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String photo;

    private Login user ;


    public Profile(){
        super();
    }

    public Profile(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="profile_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    public Login getUser(){
        return this.user;
    }
    public void setUser(Login user){
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id != null && id.equals(profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

