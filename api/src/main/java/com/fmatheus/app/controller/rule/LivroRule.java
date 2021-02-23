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

import com.fmatheus.app.controller.dto.LivroDto;
import com.fmatheus.app.controller.dto.LivroUpdateDto;
import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.event.ResourceEvent;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.model.entity.LivroEntity;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import com.fmatheus.app.model.persistence.service.impl.LivroServiceImpl;

@Component
public class LivroRule {

    private static final Logger log = LoggerFactory.getLogger(LivroRule.class);

    @Autowired
    private MessageResponseRule messageResponseRule;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LivroServiceImpl livroServiceImpl;

    /**
     * Lista os livros
     *
     * @param pageable
     * @param filter
     * @return ResponseEntity
     *
     */
    public ResponseEntity<Page<LivroDto>> findAllPaginator(Pageable pageable, RepositoryFilter filter) {
        Page<LivroEntity> employees = livroServiceImpl.findAllFilter(filter, pageable);
        return !employees.isEmpty() ? ResponseEntity.ok(employees.map(LivroDto::converterDto))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Cadastrar livro
     *
     * @param dto
     * @param response
     * @return ResponseEntity
     *
     */
    public ResponseEntity<?> create(LivroDto dto, HttpServletResponse response) {

        var livro = livroServiceImpl.findByIsbn(dto.getIsbn());
        if (livro != null) {
            String message = MessagesEnum.ERROR_ISBN_EXIST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var entity = new LivroDto().create(dto);
        entity = livroServiceImpl.save(entity);   
        
      //  LivroEntity l=livroServiceImpl.findById(entity.getId()).orElse(null);

        publisher.publishEvent(new ResourceEvent(this, response, entity.getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LivroDto.converterDto(entity));
    }

    /**
     * Atualizar livro
     *
     * @param id
     * @param dto
     * @param response
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> update(int id, LivroUpdateDto dto, HttpServletResponse response) {

        /* Verifica se ao ID do livro existe */
        var livro = livroServiceImpl.findById(id).orElse(null);
        if (livro == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var entity = new LivroUpdateDto().update(livro, dto);
        entity = livroServiceImpl.save(entity);

        publisher.publishEvent(new ResourceEvent(this, response, livro.getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LivroDto.converterDto(entity));
    }

    /**
     * Pesquisar livro
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> findById(int id) {

        var livro = livroServiceImpl.findById(id).orElse(null);
        if (livro == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        var livroDto = LivroDto.converterDto(livro);

        return livro != null ? ResponseEntity.ok(livroDto)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(messageResponseRule.messageResponseBadRequest());

    }

    /**
     * Excluir livro
     *
     * @param id
     * @return ResponseEntity
     * @throws BadRequestException
     */
    public ResponseEntity<?> delete(int id) {

        /* Verifica se ao ID do livro existe */
        var livro = livroServiceImpl.findById(id).orElse(null);
        if (livro == null) {
            String message = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
            log.warn(message);
            throw new BadRequestException(message);
        }

        livroServiceImpl.delete(livro);

        return ResponseEntity.status(HttpStatus.OK)
                .body(messageResponseRule.messageResponseSuccessDelete());
    }

}
