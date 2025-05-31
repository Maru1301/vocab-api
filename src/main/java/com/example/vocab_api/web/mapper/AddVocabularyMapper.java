package com.example.vocab_api.web.mapper;

import com.example.vocab_api.application.dto.AddVocabularyDto;
import com.example.vocab_api.web.dto.AddVocabularyRequest;

public class AddVocabularyMapper {
    public static AddVocabularyDto toDto(AddVocabularyRequest request) {
        if (request == null) {
            return null;
        }
        return new AddVocabularyDto(
            request.getJp(),
            request.getZh(),
            request.getPartOfSpeech(),
            request.getNotes()
        );
    }
}
