package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByJudge0id(Integer judge0id);
    boolean existsById(Integer id);
}
