package com.example.vocab_api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.domain.repository.IVocabularyRepository;
import com.example.vocab_api.domain.service.VocabularyDomainService;

@Service

public class VocabularyService {
    private final IVocabularyRepository repo;
    private final VocabularyDomainService domainService;

    @Autowired
    public VocabularyService(IVocabularyRepository repo, VocabularyDomainService domainService) {
        this.repo = repo;
        this.domainService = domainService;
    }

    public boolean saveVocab(AddVocabularyDto dto) {
        if (dto == null || !domainService.validateVocabulary(dto.getJp(), dto.getZh())) {
            System.err.println("Japanese word and Chinese meaning must not be empty.");
            return false;
        }
        try {
            Vocabulary v = new Vocabulary();
            domainService.updateVocabularyFields(v, dto.getJp(), dto.getZh(), dto.getPartOfSpeech(), dto.getNotes());
            repo.save(v);
        } catch (Exception e) {
            System.err.println("Error saving vocabulary: " + e.getMessage());
            return false;
        }
        return true;
    }

    public java.util.List<Vocabulary> listAll() {
        return repo.findAllByOrderByCreatedAtDesc();
    }

    public Vocabulary getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public java.util.List<Vocabulary> search(String keyword) {
        return repo.findByJapaneseWordContainingIgnoreCaseOrChineseMeaningContainingIgnoreCaseOrPartOfSpeechContainingIgnoreCaseOrNotesContainingIgnoreCase(
            keyword, keyword, keyword, keyword);
    }

    public boolean updateVocabulary(Long id, AddVocabularyDto dto) {
        Vocabulary v = repo.findById(id).orElse(null);
        if (v == null) return false;
        domainService.updateVocabularyFields(v, dto.getJp(), dto.getZh(), dto.getPartOfSpeech(), dto.getNotes());
        repo.save(v);
        return true;
    }

    public boolean deleteVocabulary(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
