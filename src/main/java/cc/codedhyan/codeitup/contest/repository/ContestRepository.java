package cc.codedhyan.codeitup.contest.repository;

import cc.codedhyan.codeitup.contest.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest,String> {

}
