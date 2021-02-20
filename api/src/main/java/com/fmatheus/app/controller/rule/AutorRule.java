package com.fmatheus.app.controller.rule;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fmatheus.app.controller.dto.AutorDto;
import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.event.ResourceEvent;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.service.impl.AutorServiceImpl;
import com.fmatheus.app.model.persistence.service.impl.PessoaServiceImpl;
import java.util.ArrayList;
import java.util.List;

@Component
public class AutorRule {

    private static final Logger log = LoggerFactory.getLogger(AutorRule.class);

    @Autowired
    private MessageResponseRule messageResponseRule;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private AutorServiceImpl autorServiceImpl;

    @Autowired
    private PessoaServiceImpl pessoaServiceImpl;

    /**
     * Lista os autores
     *
     * @param pageable
     * @param filter
     * @return ResponseEntity
     *
     */
    public ResponseEntity<Page<AutorDto>> findAllPaginator(Pageable pageable, RepositoryFilter filter) {
        Page<AutorEntity> employees = autorServiceImpl.findAllFilter(filter, pageable);
        return !employees.isEmpty() ? ResponseEntity.ok(employees.map(AutorDto::converterDto))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Cadastrar autor
     *
     * @param dto
     * @param response
     * @return ResponseEntity
     *
     */
    public ResponseEntity<?> create(AutorDto dto, HttpServletResponse response) {

        var entity = new AutorDto().create(dto);
        entity = pessoaServiceImpl.save(entity);

        publisher.publishEvent(new ResourceEvent(this, response, entity.getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AutorDto.converterDto(entity.getPessoaFisicaEntity().getAutorEntity()));
    }

    /**
     * Atualizar autor
     *
     * @param id
     * @param dto
     * @param response
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> update(int id, AutorDto dto, HttpServletResponse response) {

        /* Verifica se ao ID do autor existe */
        var autor = autorServiceImpl.findById(id).orElse(null);
        if (autor == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var pessoa = new AutorDto().update(autor.getIdPessoaFisica().getIdPessoa(), dto);
        var entity = pessoaServiceImpl.save(pessoa);

        publisher.publishEvent(new ResourceEvent(this, response, pessoa.getPessoaFisicaEntity().getAutorEntity().getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AutorDto.converterDto(entity.getPessoaFisicaEntity().getAutorEntity()));
    }

    /**
     * Pesquisar autor
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> findById(int id) {

        var autor = autorServiceImpl.findById(id).orElse(null);
        if (autor == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var autorDto = AutorDto.converterDto(autor);

        return autor != null ? ResponseEntity.ok(autorDto)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(messageResponseRule.messageResponseBadRequest());

    }

    /**
     * Excluir autor
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> delete(int id) {

        /* Verifica se ao ID do autor existe */
        var autor = autorServiceImpl.findById(id).orElse(null);
        if (autor == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        pessoaServiceImpl.delete(autor.getIdPessoaFisica().getIdPessoa());

        return ResponseEntity.status(HttpStatus.OK)
                .body(messageResponseRule.messageResponseSuccessDelete());
    }

}
