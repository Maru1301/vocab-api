package com.example.vocab_api.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.vocab_api.domain.model.Vocabulary;

class VocabularyDomainServiceTest {
    private final VocabularyDomainService service = new VocabularyDomainService();

    @Test
    void validateVocabulary_validInput_returnsTrue() {
        assertTrue(service.validateVocabulary("こんにちは", "你好"));
    }

    @Test
    void validateVocabulary_nullJapanese_returnsFalse() {
        assertFalse(service.validateVocabulary(null, "你好"));
    }

    @Test
    void validateVocabulary_emptyJapanese_returnsFalse() {
        assertFalse(service.validateVocabulary("   ", "你好"));
    }

    @Test
    void validateVocabulary_nullChinese_returnsFalse() {
        assertFalse(service.validateVocabulary("こんにちは", null));
    }

    @Test
    void validateVocabulary_emptyChinese_returnsFalse() {
        assertFalse(service.validateVocabulary("こんにちは", "   "));
    }

    @Test
    void updateVocabularyFields_setsAllFields() {
        Vocabulary vocab = new Vocabulary();
        service.updateVocabularyFields(vocab, "ありがとう", "谢谢", "noun", "polite");
        assertEquals("ありがとう", vocab.getJapaneseWord());
        assertEquals("谢谢", vocab.getChineseMeaning());
        assertEquals("noun", vocab.getPartOfSpeech());
        assertEquals("polite", vocab.getNotes());
    }
}
