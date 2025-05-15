package com.example.vocab_api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vocab_api.domain.model.Vocabulary;

public interface IVocabularyRepository extends JpaRepository<Vocabulary, Long> {
    List<Vocabulary> findByJapaneseWord(String japaneseWord);
}
