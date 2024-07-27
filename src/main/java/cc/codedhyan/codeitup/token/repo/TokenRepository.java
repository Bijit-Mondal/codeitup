package cc.codedhyan.codeitup.token.repo;

import cc.codedhyan.codeitup.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.user_id = u.user_id\s
      where u.user_id = :user_id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String user_id);

    Optional<Token> findByToken(String token);
}
