package com.example.vocab_api.application.service;

import org.springframework.stereotype.Service;

import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.domain.repository.IVocabularyRepository;

@Service
public class VocabularyService {
    private final IVocabularyRepository repo;

    public VocabularyService(IVocabularyRepository repo) {
        this.repo = repo;
    }

    public boolean saveVocab(String jp, String zh) {
        try {
            Vocabulary v = new Vocabulary();
            v.setJapaneseWord(jp);
            v.setChineseMeaning(zh);
            repo.save(v); // ← JPA 幫你存進資料庫
        } catch (Exception e) {
            // Log the exception or handle it as needed
            System.err.println("Error saving vocabulary: " + e.getMessage());
            // Optionally, rethrow or handle differently

            return false;
        }

        return true;
    }
}
