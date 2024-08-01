package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.LanguageRequest;
import cc.codedhyan.codeitup.problem.model.Language;
import cc.codedhyan.codeitup.problem.service.LanguageService;
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
@RequestMapping("/api/v1/admin/language")
@Slf4j
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping("")
    public ResponseEntity<Language> addLanguage(
            @RequestBody @Valid LanguageRequest language
    ){
        log.info("Adding language: {}", language);
        return ResponseEntity.ok(languageService.addLanguage(language));
    }
}