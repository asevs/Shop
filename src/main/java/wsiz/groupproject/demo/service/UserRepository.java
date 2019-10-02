package wsiz.groupproject.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import wsiz.groupproject.demo.model.User;

import java.util.Optional;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByPrincipalId(String principalId);
    Optional<User> findByEmail(String email);

    User findByEmail(Object email);
}
