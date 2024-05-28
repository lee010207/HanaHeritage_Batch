package com.heeha.domain.base.service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BaseService<V, K> {
    @Transactional
    public V insert(V v);

    @Transactional
    public Boolean delete(K k);

    @Transactional
    public V update(V v);

    public Optional<V> get(K k);

    public List<V> getAll();
}