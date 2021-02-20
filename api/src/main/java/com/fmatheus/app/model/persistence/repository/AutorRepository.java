package com.fmatheus.app.model.persistence.repository;

import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.persistence.repository.query.AutorRepositoryQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends BaseRepository<AutorEntity, Integer>, AutorRepositoryQuery {

}
