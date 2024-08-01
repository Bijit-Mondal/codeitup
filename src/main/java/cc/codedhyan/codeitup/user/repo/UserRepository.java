package cc.codedhyan.codeitup.user.repo;

import cc.codedhyan.codeitup.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    User getByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByProfile(String profile);
}


