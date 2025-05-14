package com.example.vocab_api.Service;

import org.springframework.stereotype.Service;

import com.example.vocab_api.Entity.Vocabulary;
import com.example.vocab_api.Repository.Interface.IVocabularyRepository;

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
