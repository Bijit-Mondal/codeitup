package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.Judge0Response;
import cc.codedhyan.codeitup.problem.service.SubmissionCallbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/open/submission-callback")
@Slf4j
@CrossOrigin("http://45.79.123.242:2358")
public class SubmissionCallbackController {
    private final SubmissionCallbackService submissionCallbackService;

    @PutMapping("")
    public void submissionCallback(
            @RequestBody @Valid Judge0Response response
    ){
        log.info("Submission callback received: {}", response);
        submissionCallbackService.processSubmissionCallback(response);
    }
}
