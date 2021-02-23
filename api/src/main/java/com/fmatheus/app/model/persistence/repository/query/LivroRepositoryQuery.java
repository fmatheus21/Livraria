package com.fmatheus.app.model.persistence.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fmatheus.app.model.entity.LivroEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;

public interface LivroRepositoryQuery {

    Page<LivroEntity> filter(RepositoryFilter filter, Pageable pageable);

}
