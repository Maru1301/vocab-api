package com.example.vocab_api.web.dto;

import jakarta.validation.constraints.NotBlank;

public class AddVocabularyRequest {
    @NotBlank(message = "Japanese word must not be empty")
    private String jp;
    @NotBlank(message = "Chinese meaning must not be empty")
    private String zh;

    public AddVocabularyRequest() {}

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
