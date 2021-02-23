package com.fmatheus.app.model.persistence.repository.impl;

import com.fmatheus.app.controller.enumerable.AtributoEnum;
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

import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.entity.PessoaJuridicaEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.repository.query.EditoraRepositoryQuery;
import javax.persistence.criteria.Join;

public class EditoraRepositoryImpl implements EditoraRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<EditoraEntity> filter(RepositoryFilter filter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<EditoraEntity> criteriaQuery = builder.createQuery(EditoraEntity.class);
        Root<EditoraEntity> root = criteriaQuery.from(EditoraEntity.class);
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<EditoraEntity> typedQuery = manager.createQuery(criteriaQuery);

        this.addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));

    }

    private Predicate[] createRestrictions(RepositoryFilter filter, CriteriaBuilder builder,
            Root<EditoraEntity> root) {

        List<Predicate> predicates = new ArrayList<>();
      
        Join<PessoaJuridicaEntity, EditoraEntity> join = root.join(AtributoEnum.ID_PESSOA_JURIDICA.getDescription());
       
        if (!StringUtils.isEmpty(filter.getRazaoSocial())) {
            predicates.add(builder.like(builder.lower(join.<String>get(AtributoEnum.RAZAO_SOCIAL.getDescription())),
                    "%" + filter.getRazaoSocial().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }

    private void addPageRestrictions(TypedQuery<EditoraEntity> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

    private Long total(RepositoryFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<EditoraEntity> root = criteriaQuery.from(EditoraEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
