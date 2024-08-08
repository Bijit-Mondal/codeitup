package cc.codedhyan.codeitup.contest.repository;

import cc.codedhyan.codeitup.contest.model.ContestSubmission;
import cc.codedhyan.codeitup.contest.model.ContestSubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestSubmissionRepository extends JpaRepository<ContestSubmission, ContestSubmissionId> {
}
