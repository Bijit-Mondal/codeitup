package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.ProblemRequest;
import cc.codedhyan.codeitup.problem.service.ProblemService;
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
@RequestMapping("/api/v1/admin/problem")
@Slf4j
public class ProblemAdminController {
    private final ProblemService problemService;

    @PostMapping("")
    public ResponseEntity<?> addProblem(
            @RequestBody @Valid ProblemRequest problem
    ){
        log.info("Adding problem: {}", problem);
        return ResponseEntity.ok(problemService.addProblem(problem));
    }
}
