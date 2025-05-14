package com.example.vocab_api.Service;

@Services
public class VocabularyService {
    private final VocabularyRepository repo;

    public VocabularyService(VocabularyRepository repo) {
        this.repo = repo;
    }

    public void saveVocab(String jp, String zh) {
        Vocabulary v = new Vocabulary();
        v.setJapaneseWord(jp);
        v.setChineseMeaning(zh);
        repo.save(v); // ← JPA 幫你存進資料庫
    }
}
