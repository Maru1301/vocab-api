package com.example.vocab_api.application.dto;

public class AddVocabularyDto {
    private String jp;
    private String zh;

    public AddVocabularyDto() {}

    public AddVocabularyDto(String jp, String zh) {
        this.jp = jp;
        this.zh = zh;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }
}
