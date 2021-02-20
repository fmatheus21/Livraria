package com.fmatheus.app.model.persistence.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;

public interface AutorRepositoryQuery {

    Page<AutorEntity> filter(RepositoryFilter filter, Pageable pageable);

}
