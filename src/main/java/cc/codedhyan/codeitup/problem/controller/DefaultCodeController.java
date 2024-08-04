package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.DefaultCodeRequest;
import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.service.DefaultCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/default-code")
@Slf4j
public class DefaultCodeController {
    private final DefaultCodeService defaultCodeService;

    @PostMapping
    public ResponseEntity<?> addDefaultCode(
            @RequestBody @Valid DefaultCodeRequest defaultCodeRequest
    ){
        log.info("Adding default code");
        defaultCodeService.addDefaultCode(defaultCodeRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{problemId}/{languageId}")
    public ResponseEntity<?> updateDefaultCode(
            @PathVariable String problemId,
            @PathVariable Integer languageId,
            @RequestBody @Valid DefaultCodeRequest defaultCode
    ) {
        log.info("Updating default code for problemId: {}, languageId: {}", problemId, languageId);
        return ResponseEntity.ok(defaultCodeService.updateDefaultCode(problemId, languageId, defaultCode));
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<List<DefaultCode>> getDefaultCodes(
            @PathVariable String problemId
    ){
        log.info("Getting default codes for problem with id: {}", problemId);
        return ResponseEntity.ok(defaultCodeService.getDefaultCodes(problemId));
    }

    @DeleteMapping("/{problemId}")
    public ResponseEntity<?> deleteAllDefaultCodes(
            @PathVariable String problemId
    ){
        log.info("Deleting all default codes for problem with id: {}", problemId);
        defaultCodeService.deleteAllDefaultCodes(problemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{problemId}/{languageId}")
    public ResponseEntity<?> deleteDefaultCode(
            @PathVariable String problemId,
            @PathVariable Integer languageId
    ){
        log.info("Deleting default code for problem with id: {} and language with id: {}", problemId, languageId);
        defaultCodeService.deleteDefaultCode(problemId, languageId);
        return ResponseEntity.ok().build();
    }
}
