package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.SubmissionRequest;
import cc.codedhyan.codeitup.problem.SubmissionResponse;
import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.problem.repository.DefaultCodeRepository;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import cc.codedhyan.codeitup.problem.repository.SubmissionRepository;
import cc.codedhyan.codeitup.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    @Value("${application.security.github.pat}")
    private String PAT_KEY;
    @Value("${application.security.rapidapi.key}")
    private String RAPIDAPI_KEY;
    @Value("${application.security.rapidapi.host}")
    private String RAPIDAPI_HOST;

    private final DefaultCodeRepository defaultCodeRepository;
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;

    public SubmissionResponse createSubmission(SubmissionRequest request) {
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Problem problem = problemRepository.findBySlugAndHiddenFalse(request.getProblemSlug())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem not found"));
        DefaultCode defaultCode = defaultCodeRepository.findByProblemIdAndLanguageId(problem.getId(), request.getLanguageId())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Default code for this problem not found"));
        return null;
    }
}
