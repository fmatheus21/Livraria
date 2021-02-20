package com.fmatheus.app.model.persistence.service;

import com.fmatheus.app.model.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author fmatheus
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    public abstract T save(T t);

    public abstract List<T> findAll();

    public abstract Page<T> findAllPaginator(Pageable page);

    public abstract Optional<T> findById(ID id);

    public abstract T update(T t);

    public abstract T updateById(T t, ID id);

    public abstract void delete(T t);

    public abstract void deleteById(ID id);

}
