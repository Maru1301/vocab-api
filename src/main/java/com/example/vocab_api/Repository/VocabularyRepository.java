package com.example.vocab_api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vocab_api.Entity.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    List<Vocabulary> findByJapaneseWord(String japaneseWord);
}
