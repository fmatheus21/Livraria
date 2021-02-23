package com.fmatheus.app.model.persistence.repository.impl;

import com.fmatheus.app.controller.enumerable.AtributoEnum;
import com.fmatheus.app.model.entity.EditoraEntity;
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

import com.fmatheus.app.model.entity.LivroEntity;
import com.fmatheus.app.model.entity.PessoaJuridicaEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.repository.query.LivroRepositoryQuery;
import javax.persistence.criteria.Join;

public class LivroRepositoryImpl implements LivroRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<LivroEntity> filter(RepositoryFilter filter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LivroEntity> criteriaQuery = builder.createQuery(LivroEntity.class);
        Root<LivroEntity> root = criteriaQuery.from(LivroEntity.class);
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<LivroEntity> typedQuery = manager.createQuery(criteriaQuery);

        this.addPageRestrictions(typedQuery, pageable);   

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));

    }

    private Predicate[] createRestrictions(RepositoryFilter filter, CriteriaBuilder builder,
            Root<LivroEntity> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filter.getTitulo())) {
            predicates.add(builder.like(builder.lower(root.<String>get(AtributoEnum.TITULO.getDescription())),
                    "%" + filter.getTitulo().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filter.getIsbn())) {
            predicates.add(builder.like(builder.lower(root.<String>get(AtributoEnum.ISBN.getDescription())),
                    "%" + filter.getIsbn().toLowerCase() + "%"));
        }

       Join<EditoraEntity, LivroEntity> joinEditora = root.join(AtributoEnum.ID_EDITORA.getDescription());
       Join<PessoaJuridicaEntity, EditoraEntity> joinPessoaJuridica = joinEditora.join(AtributoEnum.ID_PESSOA_JURIDICA.getDescription());

        if (!StringUtils.isEmpty(filter.getEditora())) {
            predicates.add(builder.like(builder.lower(joinPessoaJuridica.<String>get(AtributoEnum.RAZAO_SOCIAL.getDescription())),
                    "%" + filter.getEditora().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);

    }

    private void addPageRestrictions(TypedQuery<LivroEntity> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

    private Long total(RepositoryFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<LivroEntity> root = criteriaQuery.from(LivroEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
