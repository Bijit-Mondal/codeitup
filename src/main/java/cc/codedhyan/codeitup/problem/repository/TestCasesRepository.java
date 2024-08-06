package cc.codedhyan.codeitup.problem.repository;

import cc.codedhyan.codeitup.problem.model.TestCases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCasesRepository extends JpaRepository<TestCases, Integer> {

}
