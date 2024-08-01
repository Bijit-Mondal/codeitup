package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionConflict;
import cc.codedhyan.codeitup.problem.LanguageRequest;
import cc.codedhyan.codeitup.problem.model.Language;
import cc.codedhyan.codeitup.problem.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public Language addLanguage(LanguageRequest language) {
        if(languageRepository.existsByJudge0id(language.getJudge0id())){
            throw new ApiRequestExceptionConflict("Language with judge0id: "+language.getJudge0id()+" already exists");
        }
        Language lang =  Language.builder()
                .name(language.getName())
                .judge0id(language.getJudge0id())
                .build();
        languageRepository.save(lang);
        return lang;
    }
}
