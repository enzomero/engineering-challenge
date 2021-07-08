package eu.senla.status_api.dao;

import eu.senla.status_api.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<Profile, Long> {

    Profile findByUuid(String uuid);

}
