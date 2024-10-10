package ma.zyn.app.dao.facade.core.authentication;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.authentication.Login;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.authentication.Login;
import java.util.List;


@Repository
public interface LoginDao extends AbstractRepository<Login,Long>  {
    Login findByEmail(String email);
    int deleteByEmail(String email);


    @Query("SELECT NEW Login(item.id,item.email) FROM Login item")
    List<Login> findAllOptimized();

}
