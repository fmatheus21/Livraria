package com.fmatheus.app.model.persistence.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmatheus.app.model.entity.BaseEntity;
import com.fmatheus.app.model.persistence.repository.BaseRepository;
import com.fmatheus.app.model.persistence.service.BaseService;

/**
 *
 * @author fmatheus
 * @param <T>
 * @param <ID>
 */
@Service
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable>
        implements BaseService<T, ID> {

    private final BaseRepository<T, ID> baseRepository;

    @Autowired
    public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T t) {
        return (T) baseRepository.save(t);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<T> findAllPaginator(Pageable page) {
        return baseRepository.findAll(page);
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public T update(T t) {
        return (T) baseRepository.save(t);
    }

    @Override
    public T updateById(T t, ID id) {
        Optional<T> optional = baseRepository.findById(id);
        if (optional.isPresent()) {
            return (T) baseRepository.save(t);
        } else {
            return null;
        }
    }

    @Override
    public void delete(T t) {
        baseRepository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

}
