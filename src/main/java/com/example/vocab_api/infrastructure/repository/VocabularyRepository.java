package com.example.vocab_api.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.lang.NonNull;

import com.example.vocab_api.domain.model.Vocabulary;
import com.example.vocab_api.domain.repository.IVocabularyRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class VocabularyRepository implements  IVocabularyRepository {

     @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vocabulary> findByJapaneseWord(String japaneseWord) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAllInBatch(Iterable<Vocabulary> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vocabulary getOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vocabulary getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vocabulary getReferenceById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Vocabulary> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Vocabulary> findAllById(Iterable<Long> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @NonNull
    public <S extends Vocabulary> S save(@NonNull S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<Vocabulary> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Vocabulary entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll(Iterable<? extends Vocabulary> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Vocabulary> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Vocabulary> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Vocabulary, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}