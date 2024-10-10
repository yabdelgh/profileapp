package ma.zyn.app.dao.facade.core.profile;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profile.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProfileDao extends AbstractRepository<Profile,Long>  {

    List<Profile> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);


}
