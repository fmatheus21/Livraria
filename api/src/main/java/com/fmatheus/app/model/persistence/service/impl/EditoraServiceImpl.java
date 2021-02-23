package com.fmatheus.app.model.persistence.service.impl;

import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.persistence.repository.EditoraRepository;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.repository.impl.BaseRepositoryImpl;
import com.fmatheus.app.model.persistence.service.EditoraService;
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
public class EditoraServiceImpl extends BaseRepositoryImpl<EditoraEntity, Integer> implements EditoraService {

    @Autowired
    private EditoraRepository repository;

    public EditoraServiceImpl(EditoraRepository repository) {
        super(repository);
    }

    public Page<EditoraEntity> findAllFilter(RepositoryFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

}
