package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {
}
