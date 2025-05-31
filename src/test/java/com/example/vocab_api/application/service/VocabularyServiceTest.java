package com.example.vocab_api.application.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.domain.repository.IVocabularyRepository;

public class VocabularyServiceTest {
    @Mock
    private IVocabularyRepository repo;

    @InjectMocks
    private VocabularyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveVocab_success() {
        AddVocabularyDto dto = new AddVocabularyDto("こんにちは", "你好", "noun", "greeting");
        when(repo.save(any(Vocabulary.class))).thenAnswer(invocation -> invocation.getArgument(0));
        boolean result = service.saveVocab(dto);
        assertTrue(result);
        verify(repo).save(any(Vocabulary.class));
    }

    @Test
    void saveVocab_fail_emptyJapanese() {
        AddVocabularyDto dto = new AddVocabularyDto("", "你好", "noun", "greeting");
        boolean result = service.saveVocab(dto);
        assertFalse(result);
        verify(repo, never()).save(any());
    }

    @Test
    void saveVocab_fail_emptyChinese() {
        AddVocabularyDto dto = new AddVocabularyDto("こんにちは", "", "noun", "greeting");
        boolean result = service.saveVocab(dto);
        assertFalse(result);
        verify(repo, never()).save(any());
    }

    @Test
    void listAll_returnsVocabList() {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        when(repo.findAllByOrderByCreatedAtDesc()).thenReturn(Collections.singletonList(vocab));
        assertEquals(1, service.listAll().size());
        assertEquals("こんにちは", service.listAll().get(0).getJapaneseWord());
    }

    @Test
    void getById_found() {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        when(repo.findById(1L)).thenReturn(Optional.of(vocab));
        Vocabulary result = service.getById(1L);
        assertNotNull(result);
        assertEquals("こんにちは", result.getJapaneseWord());
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        Vocabulary result = service.getById(1L);
        assertNull(result);
    }

    @Test
    void search_returnsResults() {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        when(repo.findByJapaneseWordContainingIgnoreCaseOrChineseMeaningContainingIgnoreCaseOrPartOfSpeechContainingIgnoreCaseOrNotesContainingIgnoreCase(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(vocab));
        assertEquals(1, service.search("こん").size());
    }

    @Test
    void updateVocabulary_success() {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("old");
        when(repo.findById(1L)).thenReturn(Optional.of(vocab));
        when(repo.save(any(Vocabulary.class))).thenReturn(vocab);
        AddVocabularyDto dto = new AddVocabularyDto("new", "新しい", "noun", "note");
        boolean result = service.updateVocabulary(1L, dto);
        assertTrue(result);
        assertEquals("new", vocab.getJapaneseWord());
        assertEquals("新しい", vocab.getChineseMeaning());
        assertEquals("noun", vocab.getPartOfSpeech());
        assertEquals("note", vocab.getNotes());
    }

    @Test
    void updateVocabulary_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        AddVocabularyDto dto = new AddVocabularyDto("new", "新しい", "noun", "note");
        boolean result = service.updateVocabulary(1L, dto);
        assertFalse(result);
        verify(repo, never()).save(any());
    }

    @Test
    void deleteVocabulary_success() {
        when(repo.existsById(1L)).thenReturn(true);
        doNothing().when(repo).deleteById(1L);
        boolean result = service.deleteVocabulary(1L);
        assertTrue(result);
        verify(repo).deleteById(1L);
    }

    @Test
    void deleteVocabulary_notFound() {
        when(repo.existsById(1L)).thenReturn(false);
        boolean result = service.deleteVocabulary(1L);
        assertFalse(result);
        verify(repo, never()).deleteById(anyLong());
    }
}
