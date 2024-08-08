package cc.codedhyan.codeitup.contest.repository;

import cc.codedhyan.codeitup.contest.model.ContestProblem;
import cc.codedhyan.codeitup.contest.model.ContestProblemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestProblemRepository extends JpaRepository<ContestProblem, ContestProblemId> {

}
