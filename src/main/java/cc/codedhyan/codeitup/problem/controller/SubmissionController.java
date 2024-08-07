package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.SubmissionRequest;
import cc.codedhyan.codeitup.problem.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{submissionId}")
    public ResponseEntity<?> getSubmission(
            @PathVariable String submissionId
    ){
        log.info("Getting submission: {}", submissionId);
        return ResponseEntity.ok(submissionService.getSubmission(submissionId));
    }

    @GetMapping("/problem/{problemSlug}")
    public ResponseEntity<?> getSubmissions(
            @PathVariable String problemSlug
    ){
        log.info("Getting all submissions");
        return ResponseEntity.ok(submissionService.getSubmissions(problemSlug));
    }
}