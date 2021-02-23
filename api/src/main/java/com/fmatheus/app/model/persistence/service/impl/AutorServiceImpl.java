package com.fmatheus.app.model.persistence.service.impl;

import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.persistence.repository.AutorRepository;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.service.AutorService;
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
public class AutorServiceImpl extends BaseServiceImpl<AutorEntity, Integer> implements AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorServiceImpl(AutorRepository repository) {
        super(repository);
    }

    public Page<AutorEntity> findAllFilter(RepositoryFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

}
