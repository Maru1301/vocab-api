package com.example.vocab_api.domain.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String japaneseWord;
    private String chineseMeaning;
    private String partOfSpeech;
    private String notes;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getJapaneseWord() {
        return japaneseWord;
    }

    public void setJapaneseWord(String jp) {
        this.japaneseWord = jp;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String zh) {
        this.chineseMeaning = zh;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @jakarta.persistence.PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
