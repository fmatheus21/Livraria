package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.dto.LivroDto;
import com.fmatheus.app.controller.dto.LivroUpdateDto;
import com.fmatheus.app.controller.rule.LivroRule;
import com.fmatheus.app.model.persistence.repository.RepositoryFilter;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author fmatheus
 */
@RestController
@RequestMapping("/livros")
public class LivroResource {

    @Autowired
    private LivroRule livroRule;

    @ApiOperation(value = "Listar livros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornar Lista"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping()
    public ResponseEntity<Page<LivroDto>> list(Pageable pageable, RepositoryFilter filter) {
        return livroRule.findAllPaginator(pageable, filter);
    }

    @ApiOperation(value = "Visualizar um livro")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o Registro"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return livroRule.findById(id);
    }

    @ApiOperation(value = "Registrar novo livro")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Criado"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid LivroDto dto, HttpServletResponse response) {
        return livroRule.create(dto, response);
    }

    @ApiOperation(value = "Atualizar livro")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody @Valid LivroUpdateDto dto, HttpServletResponse response) {
        return livroRule.update(id, dto, response);
    }

    @ApiOperation(value = "Ecluir livro")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return livroRule.delete(id);
    }

}
