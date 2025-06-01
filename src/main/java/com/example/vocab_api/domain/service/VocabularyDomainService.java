package com.example.vocab_api.domain.service;

import org.springframework.stereotype.Service;

import com.example.vocab_api.domain.model.Vocabulary;

@Service
public class VocabularyDomainService {
    public boolean validateVocabulary(String jp, String zh) {
        return jp != null && !jp.trim().isEmpty() && zh != null && !zh.trim().isEmpty();
    }

    public void updateVocabularyFields(Vocabulary v, String jp, String zh, String partOfSpeech, String notes) {
        v.setJapaneseWord(jp);
        v.setChineseMeaning(zh);
        v.setPartOfSpeech(partOfSpeech);
        v.setNotes(notes);
    }
}
