package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,String> {
    boolean existsBySlug(String slug);
    boolean existsById(String id);
    Page<Problem> findByHiddenFalse(PageRequest pageRequest);
    Optional<Problem> findBySlugAndHiddenFalse(String slug);
}
