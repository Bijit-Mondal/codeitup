package cc.codedhyan.codeitup.problem.controller;

import cc.codedhyan.codeitup.problem.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/open/language")
@Slf4j
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping("")
    public ResponseEntity<?> getLanguages() {
        return ResponseEntity.ok(languageService.getLanguages());
    }
}