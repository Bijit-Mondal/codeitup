package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionConflict;
import cc.codedhyan.codeitup.problem.ProblemRequest;
import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    public Problem addProblem(ProblemRequest problem) {
        if(problemRepository.existsBySlug(problem.getSlug())){
            throw new ApiRequestExceptionConflict("Problem with slug: "+problem.getSlug()+" already exists");
        }
        Problem prob =  Problem.builder()
                .title(problem.getTitle())
                .slug(problem.getSlug())
                .description(problem.getDescription())
                .testCases(problem.getTestCases())
                .difficulty(problem.getDifficulty())
                .solutionGist(problem.getSolutionGist())
                .solutionTutorial(problem.getSolutionTutorial())
                .build();
        problemRepository.save(prob);
        return prob;
    }
}
