package com.fmatheus.app.model.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.fmatheus.app.model.entity.BaseEntity;
import java.io.Serializable;

/**
 *
 * @author fmatheus
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {

}
