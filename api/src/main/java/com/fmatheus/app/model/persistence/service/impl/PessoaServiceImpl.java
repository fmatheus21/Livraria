package com.fmatheus.app.model.persistence.service.impl;

import com.fmatheus.app.model.entity.PessoaEntity;
import com.fmatheus.app.model.persistence.repository.PessoaRepository;
import com.fmatheus.app.model.persistence.repository.impl.BaseRepositoryImpl;
import com.fmatheus.app.model.persistence.service.PessoaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fmatheus
 */
@Service
@Transactional
public class PessoaServiceImpl extends BaseRepositoryImpl<PessoaEntity, Integer> implements PessoaService {

    public PessoaServiceImpl(PessoaRepository repository) {
        super(repository);
    }

}
