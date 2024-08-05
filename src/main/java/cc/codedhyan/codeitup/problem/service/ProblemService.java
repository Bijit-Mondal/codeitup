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

    public Problem toggleHideProblem(String slug) {
        Problem prob = problemRepository.findBySlug(slug)
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem with slug: "+slug+" not found"));
        // if the problem doesn't have default code then it can't be hidden and can't be set to unhidden
        if(prob.getDefaultCode() == null && !prob.getHidden()){
            throw new ApiRequestExceptionConflict("Problem with slug: "+slug+" can't be hidden or unhidden");
        }
        prob.setHidden(!prob.getHidden());
        problemRepository.save(prob);
        return prob;
    }

    public Problem updateProblem(String slug, ProblemRequest problem) {
        Problem prob = problemRepository.findBySlug(slug)
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem with slug: "+slug+" not found"));
        prob.setTitle(problem.getTitle());
        prob.setDescription(problem.getDescription());
        prob.setTestCases(problem.getTestCases());
        prob.setDifficulty(problem.getDifficulty());
        prob.setSolutionGist(problem.getSolutionGist());
        prob.setSolutionTutorial(problem.getSolutionTutorial());
        problemRepository.save(prob);
        return prob;
    }

    public Page<Problem> getNonHiddenProblems(int page, int size, String sort) {
        Sort sortObject = Sort.by(Sort.Direction.DESC, sort != null ? sort : "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortObject);
        return problemRepository.findByHiddenFalse(pageRequest);
    }

    public Page<Problem> getAllProblems(int page, int size, String sort) {
        Sort sortObject = Sort.by(Sort.Direction.DESC, sort != null ? sort : "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortObject);
        return problemRepository.findAll(pageRequest);
    }

    public ProblemResponse getNonHiddenProblemBySlug(String slug) {
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

    public ProblemResponse getProblemBySlug(String slug) {
        Problem problem = problemRepository.findBySlug(slug)
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem with slug: "+slug+" not found"));
        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .slug(problem.getSlug())
                .testCases(problem.getTestCases())
                .description(problem.getDescription())
                .hidden(problem.getHidden())
                .difficulty(problem.getDifficulty())
                .defaultCode(problem.getDefaultCode())
                .solutionGist(problem.getSolutionGist())
                .solutionTutorial(problem.getSolutionTutorial())
                .build();
    }

}
