package com.example.vocab_api.Entity;

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

    public void setJapaneseWord(String jp) {
        japaneseWord = jp;
    }

    public void setChineseMeaning(String zh) {
        chineseMeaning = zh;
    }
}
