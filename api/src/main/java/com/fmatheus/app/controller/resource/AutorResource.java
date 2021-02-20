package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.dto.AutorDto;
import com.fmatheus.app.controller.rule.AutorRule;
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
@RequestMapping("/autores")
public class AutorResource {

    @Autowired
    private AutorRule autorRule;

    @ApiOperation(value = "Listar autores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping()
    public ResponseEntity<Page<AutorDto>> list(Pageable pageable, RepositoryFilter filter) {
        return autorRule.findAllPaginator(pageable, filter);
    }

    @ApiOperation(value = "Visualizar um autor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o Registro"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return autorRule.findById(id);
    }

    @ApiOperation(value = "Registrar novo autor")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Criado"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid AutorDto dto, HttpServletResponse response) {
        return autorRule.create(dto, response);
    }

    @ApiOperation(value = "Atualizar autor")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody @Valid AutorDto dto, HttpServletResponse response) {
        return autorRule.update(id, dto, response);
    }

    @ApiOperation(value = "Ecluir autor")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro Atualizado"),
        @ApiResponse(code = 400, message = "Solicitação Inválida"),
        @ApiResponse(code = 500, message = "Erro no Servidor"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return autorRule.delete(id);
    }

}
