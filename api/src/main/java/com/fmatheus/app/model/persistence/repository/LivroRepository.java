package com.fmatheus.app.model.persistence.repository;

import com.fmatheus.app.model.entity.LivroEntity;
import com.fmatheus.app.model.persistence.repository.query.LivroRepositoryQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends BaseRepository<LivroEntity, Integer>, LivroRepositoryQuery {

    LivroEntity findByIsbn(String isbn);

}
