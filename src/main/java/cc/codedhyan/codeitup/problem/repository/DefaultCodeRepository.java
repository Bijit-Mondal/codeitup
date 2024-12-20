package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.model.DefaultCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefaultCodeRepository extends JpaRepository<DefaultCode, DefaultCodeId> {
    boolean existsByProblemIdAndLanguageId(String problemId, Integer languageId);
    List<DefaultCode> findByProblemId(String problemId);
    Optional<DefaultCode> findByProblemIdAndLanguageId(String problemId, Integer languageId);
    void deleteAllByProblemId(String problemId);
    void deleteByProblemIdAndLanguageId(String problemId, Integer languageId);
}
