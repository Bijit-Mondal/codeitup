package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.LanguageRequest;
import cc.codedhyan.codeitup.problem.model.Language;
import cc.codedhyan.codeitup.problem.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/open/language")
@Slf4j
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Language> addLanguage(
            @RequestBody @Valid LanguageRequest language
    ){
        log.info("Adding language: {}", language);
        return ResponseEntity.ok(languageService.addLanguage(language));
    }

    @GetMapping("")
    public ResponseEntity<?> getLanguages() {
        return ResponseEntity.ok(languageService.getLanguages());
    }
}