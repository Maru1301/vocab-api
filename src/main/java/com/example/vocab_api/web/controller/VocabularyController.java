package com.example.vocab_api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.application.service.VocabularyService;
import com.example.vocab_api.web.dto.AddVocabularyRequest;
import com.example.vocab_api.web.mapper.AddVocabularyMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vocabulary")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @Autowired
    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @PostMapping("/add")
    public boolean addVocabulary(@RequestBody @Valid AddVocabularyRequest request) {
        AddVocabularyDto dto = AddVocabularyMapper.toDto(request);
        return vocabularyService.saveVocab(dto);
    }

    @GetMapping("/list")
    public java.util.List<com.example.vocab_api.domain.model.Vocabulary> listVocabulary() {
        return vocabularyService.listAll();
    }

    @GetMapping("/detail/{id}")
    public com.example.vocab_api.domain.model.Vocabulary getVocabulary(@PathVariable Long id) {
        return vocabularyService.getById(id);
    }

    @GetMapping("/search")
    public java.util.List<com.example.vocab_api.domain.model.Vocabulary> searchVocabulary(@RequestParam String keyword) {
        return vocabularyService.search(keyword);
    }

    @PutMapping("/edit/{id}")
    public boolean editVocabulary(@PathVariable Long id, @RequestBody @Valid AddVocabularyRequest request) {
        AddVocabularyDto dto = AddVocabularyMapper.toDto(request);
        return vocabularyService.updateVocabulary(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteVocabulary(@PathVariable Long id) {
        return vocabularyService.deleteVocabulary(id);
    }
}