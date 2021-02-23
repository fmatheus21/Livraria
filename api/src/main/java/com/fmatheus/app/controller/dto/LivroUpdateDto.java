package com.fmatheus.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.entity.LivroEntity;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
public class LivroUpdateDto {

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 5, max = 45)
    @JsonProperty("titulo")
    @ApiModelProperty(notes = "Título do livro", required = true)
    private String titulo;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10, max = 20)
    @JsonProperty("isbn")
    @ApiModelProperty(notes = "ISBN", required = true)
    private String isbn;

    @Getter
    @Setter
    @NotNull
    @JsonProperty("preco")
    @ApiModelProperty(notes = "ISBN", required = true)
    private BigDecimal preco;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @JsonProperty("data_publicacao")
    @ApiModelProperty(notes = "Data de Publicação", required = true)
    private String dataPublicacao;

    @Getter
    @Setter
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("id_editora")
    @ApiModelProperty(notes = "ID da editora", required = true)
    private int idEditora;

    public LivroEntity update(LivroEntity livro, LivroUpdateDto dto) {

        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setPreco(dto.getPreco());
        livro.setDataCadastro(LocalDatetUtil.currentDateTime());
        livro.setDataAlteracao(LocalDatetUtil.currentDateTime());
        livro.setIdEditora(new EditoraEntity(dto.getIdEditora()));

        return livro;

    }

}
