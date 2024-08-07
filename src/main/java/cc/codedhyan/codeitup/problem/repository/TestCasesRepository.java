package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.TestCases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestCasesRepository extends JpaRepository<TestCases, Integer> {
    Optional<TestCases> findByJudge0TrackingId(String judge0TrackingId);

    List<TestCases> findBySubmissionId(String submissionId);
}
