package com.example.vocab_api.application.service;

import org.springframework.stereotype.Service;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.domain.repository.IVocabularyRepository;

@Service
public class VocabularyService {
    private final IVocabularyRepository repo;

    public VocabularyService(IVocabularyRepository repo) {
        this.repo = repo;
    }

    public boolean saveVocab(AddVocabularyDto dto) {
        // Validate input
        if (dto == null || dto.getJp() == null || dto.getJp().trim().isEmpty()) {
            System.err.println("Japanese word must not be empty.");
            return false;
        }
        if (dto.getZh() == null || dto.getZh().trim().isEmpty()) {
            System.err.println("Chinese meaning must not be empty.");
            return false;
        }
        try {
            Vocabulary v = new Vocabulary();
            v.setJapaneseWord(dto.getJp());
            v.setChineseMeaning(dto.getZh());
            v.setPartOfSpeech(dto.getPartOfSpeech());
            v.setNotes(dto.getNotes());
            repo.save(v); // ← JPA 幫你存進資料庫
        } catch (Exception e) {
            // Log the exception or handle it as needed
            System.err.println("Error saving vocabulary: " + e.getMessage());
            // Optionally, rethrow or handle differently
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
        v.setJapaneseWord(dto.getJp());
        v.setChineseMeaning(dto.getZh());
        v.setPartOfSpeech(dto.getPartOfSpeech());
        v.setNotes(dto.getNotes());
        repo.save(v);
        return true;
    }

    public boolean deleteVocabulary(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
