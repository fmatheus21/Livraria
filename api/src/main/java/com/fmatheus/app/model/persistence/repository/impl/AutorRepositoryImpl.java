package com.fmatheus.app.model.persistence.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.entity.PessoaFisicaEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.repository.query.AutorRepositoryQuery;
import javax.persistence.criteria.Join;

public class AutorRepositoryImpl implements AutorRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<AutorEntity> filter(RepositoryFilter filter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<AutorEntity> criteriaQuery = builder.createQuery(AutorEntity.class);
        Root<AutorEntity> root = criteriaQuery.from(AutorEntity.class);
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<AutorEntity> typedQuery = manager.createQuery(criteriaQuery);

        this.addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));

    }

    private Predicate[] createRestrictions(RepositoryFilter filter, CriteriaBuilder builder,
            Root<AutorEntity> root) {

        List<Predicate> predicates = new ArrayList<>();
      
        Join<PessoaFisicaEntity, AutorEntity> join = root.join("idPessoaFisica");
       
        if (!StringUtils.isEmpty(filter.getNome())) {
            predicates.add(builder.like(builder.lower(join.<String>get("nome")),
                    "%" + filter.getNome().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }

    private void addPageRestrictions(TypedQuery<AutorEntity> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

    private Long total(RepositoryFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<AutorEntity> root = criteriaQuery.from(AutorEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
