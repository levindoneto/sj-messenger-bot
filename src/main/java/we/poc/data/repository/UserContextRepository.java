package we.poc.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import we.poc.data.UserContext;

@Repository
public interface UserContextRepository extends CrudRepository<UserContext, String> {
}
