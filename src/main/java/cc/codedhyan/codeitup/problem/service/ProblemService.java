package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionConflict;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.ProblemRequest;
import cc.codedhyan.codeitup.problem.ProblemResponse;
import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Problem> getProblems(int page, int size, String sort) {
        Sort sortObject = Sort.by(Sort.Direction.DESC, sort != null ? sort : "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortObject);
        return problemRepository.findByHiddenFalse(pageRequest);
    }
    public ProblemResponse getProblemBySlug(String slug) {
        Problem problem = problemRepository.findBySlugAndHiddenFalse(slug)
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem with slug: "+slug+" not found"));
        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .slug(problem.getSlug())
                .description(problem.getDescription())
                .hidden(problem.getHidden())
                .difficulty(problem.getDifficulty())
                .defaultCode(problem.getDefaultCode())
                .solutionGist(problem.getSolutionGist())
                .solutionTutorial(problem.getSolutionTutorial())
                .build();
    }

}
