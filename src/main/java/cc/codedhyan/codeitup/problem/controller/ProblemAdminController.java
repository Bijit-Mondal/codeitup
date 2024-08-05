package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.ProblemRequest;
import cc.codedhyan.codeitup.problem.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("")
    public ResponseEntity<?> getProblems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(problemService.getAllProblems(page, size, sort));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getProblemBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(problemService.getProblemBySlug(slug));
    }

    @PutMapping("/{slug}")
    public ResponseEntity<?> updateProblem(
            @PathVariable String slug,
            @RequestBody @Valid ProblemRequest problem
    ){
        log.info("Updating problem: {}", problem);
        return ResponseEntity.ok(problemService.updateProblem(slug, problem));
    }

    @PutMapping("/toggle-hide/{slug}")
    public ResponseEntity<?> toggleHideProblem(@PathVariable String slug) {
        log.info("Toggling hide for problem: {}", slug);
        return ResponseEntity.ok(problemService.toggleHideProblem(slug));
    }
}
