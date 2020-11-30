package LukaszSz90.simpleapp.domain.repository;

import LukaszSz90.simpleapp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existByUsername(String username);


}
