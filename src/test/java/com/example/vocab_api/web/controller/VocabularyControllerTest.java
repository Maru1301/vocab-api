package com.example.vocab_api.web.controller;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.application.service.VocabularyService;
import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.web.dto.AddVocabularyRequest;

public class VocabularyControllerTest {
    private MockMvc mockMvc;

    @Mock
    private VocabularyService vocabularyService;

    @InjectMocks
    private VocabularyController vocabularyController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vocabularyController).build();
    }

    @Test
    public void testAddVocabulary() throws Exception {
        AddVocabularyRequest request = new AddVocabularyRequest();
        request.setJp("こんにちは");
        request.setZh("你好");
        request.setPartOfSpeech("noun");
        request.setNotes("greeting");
        when(vocabularyService.saveVocab(any(AddVocabularyDto.class))).thenReturn(true);
        mockMvc.perform(post("/api/vocabulary/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"jp\":\"こんにちは\",\"zh\":\"你好\",\"partOfSpeech\":\"noun\",\"notes\":\"greeting\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testListVocabulary() throws Exception {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        vocab.setChineseMeaning("你好");
        vocab.setPartOfSpeech("noun");
        vocab.setNotes("greeting");
        when(vocabularyService.listAll()).thenReturn(Collections.singletonList(vocab));
        mockMvc.perform(get("/api/vocabulary/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].japaneseWord").value("こんにちは"));
    }

    @Test
    public void testGetVocabulary() throws Exception {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        vocab.setChineseMeaning("你好");
        vocab.setPartOfSpeech("noun");
        vocab.setNotes("greeting");
        when(vocabularyService.getById(1L)).thenReturn(vocab);
        mockMvc.perform(get("/api/vocabulary/detail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.japaneseWord").value("こんにちは"));
    }

    @Test
    public void testSearchVocabulary() throws Exception {
        Vocabulary vocab = new Vocabulary();
        vocab.setJapaneseWord("こんにちは");
        vocab.setChineseMeaning("你好");
        vocab.setPartOfSpeech("noun");
        vocab.setNotes("greeting");
        when(vocabularyService.search("こん")).thenReturn(Arrays.asList(vocab));
        mockMvc.perform(get("/api/vocabulary/search").param("keyword", "こん"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].japaneseWord").value("こんにちは"));
    }

    @Test
    public void testEditVocabulary() throws Exception {
        when(vocabularyService.updateVocabulary(eq(1L), any(AddVocabularyDto.class))).thenReturn(true);
        mockMvc.perform(put("/api/vocabulary/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"jp\":\"こんばんは\",\"zh\":\"晚上好\",\"partOfSpeech\":\"greeting\",\"notes\":\"evening greeting\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testDeleteVocabulary() throws Exception {
        when(vocabularyService.deleteVocabulary(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/vocabulary/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
