package com.fmatheus.app.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.hateoas.link.AutorLink;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.entity.PessoaEntity;
import com.fmatheus.app.model.entity.PessoaFisicaEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"id", "nome", "data_cadastro", "data_alteracao"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Autor", description = "API Autores")
public class AutorDto extends RepresentationModel<AutorDto> {

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    @JsonProperty("nome")
    @ApiModelProperty(notes = "Nome do Autor", required = true)
    private String nome;

    /**
     * ********************* Atributos nao obrigatorios **********************
     */
    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "ID do Autor")
    private int id;

    @Getter
    @Setter
    @JsonProperty("data_cadastro")
    @ApiModelProperty(notes = "Data de Cadastro")
    private LocalDateTime dataCadastro;

    @Getter
    @Setter
    @JsonProperty("data_alteracao")
    @ApiModelProperty(notes = "Data de Alteração")
    private LocalDateTime dataAlteracao;

    public PessoaEntity create(AutorDto dto) {

        var pessoa = new PessoaEntity();
        var pessoaFisica = new PessoaFisicaEntity();
        var autor = new AutorEntity();

        pessoa.setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.setDataAlteracao(LocalDatetUtil.currentDateTime());

        pessoaFisica.setIdPessoa(pessoa);
        pessoaFisica.setNome(dto.getNome());
        pessoa.setPessoaFisicaEntity(pessoaFisica);

        autor.setIdPessoaFisica(pessoaFisica);
        autor.setDataCadastro(LocalDatetUtil.currentDateTime());
        autor.setDataAlteracao(LocalDatetUtil.currentDateTime());
        pessoaFisica.setAutorEntity(autor);

        return pessoa;

    }

    public PessoaEntity update(PessoaEntity pessoa, AutorDto dto) {

        pessoa.setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.setDataAlteracao(LocalDatetUtil.currentDateTime());

        pessoa.getPessoaFisicaEntity().setNome(dto.getNome());

        pessoa.getPessoaFisicaEntity().getAutorEntity().setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.getPessoaFisicaEntity().getAutorEntity().setDataAlteracao(LocalDatetUtil.currentDateTime());

        return pessoa;

    }

    public static AutorDto converterDto(AutorEntity entity) {

        var dto = new AutorDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getIdPessoaFisica().getNome());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAlteracao(entity.getDataAlteracao());

        var link = new AutorLink();
        dto.add(link.linkView(entity.getId()));
        dto.add(link.linkDelete(entity.getId()));
        dto.add(link.linkUpdate(entity.getId()));

        return dto;
    }

}
