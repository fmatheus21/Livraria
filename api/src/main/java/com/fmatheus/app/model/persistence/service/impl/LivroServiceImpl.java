package com.fmatheus.app.model.persistence.service.impl;

import com.fmatheus.app.model.entity.LivroEntity;
import com.fmatheus.app.model.persistence.repository.LivroRepository;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fmatheus
 */
@Service
@Transactional
public class LivroServiceImpl extends BaseServiceImpl<LivroEntity, Integer> implements LivroService {

    @Autowired
    private LivroRepository repository;

    public LivroServiceImpl(LivroRepository repository) {
        super(repository);
    }

    public Page<LivroEntity> findAllFilter(RepositoryFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    public LivroEntity findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

}
