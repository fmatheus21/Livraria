package com.fmatheus.app.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.hateoas.link.EditoraLink;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.entity.PessoaEntity;
import com.fmatheus.app.model.entity.PessoaJuridicaEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"id", "cnpj", "razao_social", "data_cadastro", "data_alteracao"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Editora", description = "API Editoras")
public class EditoraDto extends RepresentationModel<EditoraDto> {

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    @JsonProperty("razao_social")
    @ApiModelProperty(notes = "Razão Social da Editora", required = true)
    private String razaoSocial;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 14, max = 20)
    @JsonProperty("cnpj")
    @ApiModelProperty(notes = "CNPJ da Editora", required = true)
    private String cnpj;

    /**
     * ********************* Atributos nao obrigatorios **********************
     */
    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "ID da Editora")
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

    public PessoaEntity create(EditoraDto dto) {

        var pessoa = new PessoaEntity();
        var pessoaJuridica = new PessoaJuridicaEntity();
        var editora = new EditoraEntity();

        pessoa.setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.setDataAlteracao(LocalDatetUtil.currentDateTime());

        pessoaJuridica.setIdPessoa(pessoa);
        pessoaJuridica.setRazaoSocial(dto.getRazaoSocial());
        pessoaJuridica.setCnpj(dto.getCnpj());
        pessoa.setPessoaJuridicaEntity(pessoaJuridica);

        editora.setIdPessoaJuridica(pessoaJuridica);
        editora.setDataCadastro(LocalDatetUtil.currentDateTime());
        editora.setDataAlteracao(LocalDatetUtil.currentDateTime());
        pessoaJuridica.setEditoraEntity(editora);

        return pessoa;

    }

    public PessoaEntity update(PessoaEntity pessoa, EditoraDto dto) {

        pessoa.setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.setDataAlteracao(LocalDatetUtil.currentDateTime());

        pessoa.getPessoaJuridicaEntity().setRazaoSocial(dto.getRazaoSocial());
        pessoa.getPessoaJuridicaEntity().setCnpj(dto.getCnpj());
        pessoa.getPessoaJuridicaEntity().getEditoraEntity().setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.getPessoaJuridicaEntity().getEditoraEntity().setDataAlteracao(LocalDatetUtil.currentDateTime());

        return pessoa;

    }

    public static EditoraDto converterDto(EditoraEntity entity) {

        var dto = new EditoraDto();

        dto.setId(entity.getId());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAlteracao(entity.getDataAlteracao());
        if (entity.getIdPessoaJuridica() != null) {
            dto.setRazaoSocial(entity.getIdPessoaJuridica().getRazaoSocial());
            dto.setCnpj(entity.getIdPessoaJuridica().getCnpj());
        }

        var link = new EditoraLink();
        dto.add(link.linkView(entity.getId()));
        dto.add(link.linkDelete(entity.getId()));
        dto.add(link.linkUpdate(entity.getId()));

        return dto;
    }

}
