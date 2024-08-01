package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,String> {
    boolean existsBySlug(String slug);
    boolean existsById(String id);
}
