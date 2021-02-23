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

import com.fmatheus.app.controller.dto.EditoraDto;
import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.event.ResourceEvent;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.service.impl.EditoraServiceImpl;
import com.fmatheus.app.model.persistence.service.impl.PessoaServiceImpl;

@Component
public class EditoraRule {

    private static final Logger log = LoggerFactory.getLogger(EditoraRule.class);

    @Autowired
    private MessageResponseRule messageResponseRule;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EditoraServiceImpl editoraServiceImpl;

    @Autowired
    private PessoaServiceImpl pessoaServiceImpl;

    /**
     * Lista as editoras
     *
     * @param pageable
     * @param filter
     * @return ResponseEntity
     *
     */
    public ResponseEntity<Page<EditoraDto>> findAllPaginator(Pageable pageable, RepositoryFilter filter) {
        Page<EditoraEntity> employees = editoraServiceImpl.findAllFilter(filter, pageable);
        return !employees.isEmpty() ? ResponseEntity.ok(employees.map(EditoraDto::converterDto))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Cadastrar editora
     *
     * @param dto
     * @param response
     * @return ResponseEntity
     *
     */
    public ResponseEntity<?> create(EditoraDto dto, HttpServletResponse response) {

        var entity = new EditoraDto().create(dto);
        entity = pessoaServiceImpl.save(entity);

        publisher.publishEvent(new ResourceEvent(this, response, entity.getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EditoraDto.converterDto(entity.getPessoaJuridicaEntity().getEditoraEntity()));
    }

    /**
     * Atualizar editora
     *
     * @param id
     * @param dto
     * @param response
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> update(int id, EditoraDto dto, HttpServletResponse response) {

        /* Verifica se ao ID do editora existe */
        var editora = editoraServiceImpl.findById(id).orElse(null);
        if (editora == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var pessoa = new EditoraDto().update(editora.getIdPessoaJuridica().getIdPessoa(), dto);
        var entity = pessoaServiceImpl.save(pessoa);

        publisher.publishEvent(new ResourceEvent(this, response, pessoa.getPessoaJuridicaEntity().getEditoraEntity().getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EditoraDto.converterDto(entity.getPessoaJuridicaEntity().getEditoraEntity()));
    }

    /**
     * Pesquisar editora
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> findById(int id) {

        var editora = editoraServiceImpl.findById(id).orElse(null);
        if (editora == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var editoraDto = EditoraDto.converterDto(editora);

        return editora != null ? ResponseEntity.ok(editoraDto)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(messageResponseRule.messageResponseBadRequest());

    }

    /**
     * Excluir editora
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> delete(int id) {

        /* Verifica se ao ID do editora existe */
        var editora = editoraServiceImpl.findById(id).orElse(null);
        if (editora == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        pessoaServiceImpl.delete(editora.getIdPessoaJuridica().getIdPessoa());

        return ResponseEntity.status(HttpStatus.OK)
                .body(messageResponseRule.messageResponseSuccessDelete());
    }

}
