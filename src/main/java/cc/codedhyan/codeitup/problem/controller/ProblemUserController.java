package cc.codedhyan.codeitup.problem.controller;


import cc.codedhyan.codeitup.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/open/problem")
public class ProblemUserController {
    private final ProblemService problemService;

    @GetMapping("")
    public ResponseEntity<?> getProblems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(problemService.getNonHiddenProblems(page, size, sort));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getProblemBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(problemService.getNonHiddenProblemBySlug(slug));
    }
}

