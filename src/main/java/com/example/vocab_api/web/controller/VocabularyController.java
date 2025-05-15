package com.example.vocab_api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}