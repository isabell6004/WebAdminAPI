package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.AspnetUsers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AspnetUsersRepository extends CrudRepository<AspnetUsers, String> {
    Optional<AspnetUsers> findByUserName(String UserName);
}
