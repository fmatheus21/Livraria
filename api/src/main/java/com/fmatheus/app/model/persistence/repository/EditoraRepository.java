package com.fmatheus.app.model.persistence.repository;

import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.persistence.repository.query.EditoraRepositoryQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends BaseRepository<EditoraEntity, Integer>, EditoraRepositoryQuery {

}
