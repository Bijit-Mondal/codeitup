package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.SubmissionRequest;
import cc.codedhyan.codeitup.problem.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/submission")
@Slf4j
public class SubmissionController {
    private final SubmissionService submissionService;

    @PostMapping("")
    public ResponseEntity<?> createSubmission(
            @RequestBody @Valid SubmissionRequest request
    ){
        log.info("Creating submission: {}", request);
        return ResponseEntity.ok(submissionService.createSubmission(request));
    }
}