package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionConflict;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.DefaultCodeRequest;
import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.repository.DefaultCodeRepository;
import cc.codedhyan.codeitup.problem.repository.LanguageRepository;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultCodeService {
    private final DefaultCodeRepository defaultCodeRepository;
    private final ProblemRepository problemRepository;
    private final LanguageRepository languageRepository;

    public void addDefaultCode(DefaultCodeRequest defaultCodeRequest) {
        if (!problemRepository.existsById(defaultCodeRequest.getProblemId())) {
            log.error("Problem with id: {} not found", defaultCodeRequest.getProblemId());
            throw new ApiRequestExceptionNotFound("Problem with id: " + defaultCodeRequest.getProblemId() + " not found");
        }
        if (!languageRepository.existsById(defaultCodeRequest.getLanguageId())) {
            log.error("Language with id: {} not found", defaultCodeRequest.getLanguageId());
            throw new ApiRequestExceptionNotFound("Language with id: " + defaultCodeRequest.getLanguageId() + " not found");
        }
        if (defaultCodeRepository.existsByProblemIdAndLanguageId(defaultCodeRequest.getProblemId(), defaultCodeRequest.getLanguageId())) {
            log.error("Default code for problem with id: {} and language with id: {} already exists", defaultCodeRequest.getProblemId(), defaultCodeRequest.getLanguageId());
            throw new ApiRequestExceptionConflict("Default code for problem with id: " + defaultCodeRequest.getProblemId() + " and language with id: " + defaultCodeRequest.getLanguageId() + " already exists");
        }

        DefaultCode defaultCode = DefaultCode.builder()
                .problemId(defaultCodeRequest.getProblemId())
                .languageId(defaultCodeRequest.getLanguageId())
                .code(defaultCodeRequest.getCode())
                .build();
        defaultCodeRepository.save(defaultCode);
    }

    public List<DefaultCode> getDefaultCodes(String problemId) {
        if (!problemRepository.existsById(problemId)) {
            log.error("Problem with id: {} not found", problemId);
            throw new ApiRequestExceptionNotFound("Problem with id: " + problemId + " not found");
        }
        return defaultCodeRepository.findByProblemId(problemId);
    }

    @Transactional
    public void deleteAllDefaultCodes(String problemId) {
        if (!problemRepository.existsById(problemId)) {
            log.error("Problem with id: {} not found", problemId);
            throw new ApiRequestExceptionNotFound("Problem with id: " + problemId + " not found");
        }
        defaultCodeRepository.deleteAllByProblemId(problemId);
    }

    @Transactional
    public void deleteDefaultCode(String problemId, Integer languageId) {
        if (!problemRepository.existsById(problemId)) {
            throw new ApiRequestExceptionNotFound("Problem with id: " + problemId + " not found");
        }
        if (!languageRepository.existsById(languageId)) {
            throw new ApiRequestExceptionNotFound("Language with id: " + languageId + " not found");
        }
        defaultCodeRepository.deleteByProblemIdAndLanguageId(problemId, languageId);
    }
}