package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.dto.EditoraDto;
import com.fmatheus.app.controller.rule.EditoraRule;
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
@RequestMapping("/editoras")
public class EditoraResource {

    @Autowired
    private EditoraRule editoraRule;

    @ApiOperation(value = "Listar Editoras")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornar Lista"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping()
    public ResponseEntity<Page<EditoraDto>> list(Pageable pageable, RepositoryFilter filter) {
        return editoraRule.findAllPaginator(pageable, filter);
    }

    @ApiOperation(value = "Visualizar um editora")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o Registro"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return editoraRule.findById(id);
    }

    @ApiOperation(value = "Registrar novo editora")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Criado"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid EditoraDto dto, HttpServletResponse response) {
        return editoraRule.create(dto, response);
    }

    @ApiOperation(value = "Atualizar editora")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody @Valid EditoraDto dto, HttpServletResponse response) {
        return editoraRule.update(id, dto, response);
    }

    @ApiOperation(value = "Ecluir editora")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return editoraRule.delete(id);
    }

}
