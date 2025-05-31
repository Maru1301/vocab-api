package com.example.vocab_api.application.dto;

public class AddVocabularyDto {
    private String jp;
    private String zh;
    private String partOfSpeech;
    private String notes;

    public AddVocabularyDto() {}

    public AddVocabularyDto(String jp, String zh, String partOfSpeech, String notes) {
        this.jp = jp;
        this.zh = zh;
        this.partOfSpeech = partOfSpeech;
        this.notes = notes;
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
}
