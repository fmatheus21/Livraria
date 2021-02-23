package com.fmatheus.app.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.hateoas.link.LivroLink;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.entity.EditoraEntity;
import com.fmatheus.app.model.entity.LivroAutorEntity;
import com.fmatheus.app.model.entity.LivroCriticaEntity;
import com.fmatheus.app.model.entity.LivroEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"id", "titulo", "isbn", "preco", "preco_extenso", "data_publicacao", "data_cadastro", "data_alteracao", "editora", "autores"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Livro", description = "API Livros")
public class LivroDto extends RepresentationModel<LivroDto> {

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

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @JsonProperty("autores")
    @ApiModelProperty(notes = "Autores")
    private List<AutorDto> listAutores;

    @Getter
    @Setter
    @NotNull
    @NotEmpty    
    @JsonProperty("criticas")
    @ApiModelProperty(notes = "Críticas", required = true)
    private List<CriticaDto> listCriticas;

    /**
     * ********************* Atributos nao obrigatorios **********************
     */
    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "ID do Livro")
    private int id;

    @Getter
    @Setter
    @JsonProperty("nome_editora")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @ApiModelProperty(notes = "Nome da Editora", required = true)
    private String nomeEditora;

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

    @Getter
    @Setter
    @JsonProperty("editora")
    @ApiModelProperty(notes = "Editora", required = true)
    private EditoraDto editora;

    @Getter
    @Setter
    @JsonProperty("preco_extenso")
    @ApiModelProperty(notes = "Preço por Extenso", required = true)
    private String precoExtenso;

    public LivroEntity create(LivroDto dto) {
        var livro = new LivroEntity();

        livro.setDataCadastro(LocalDatetUtil.currentDateTime());
        livro.setDataAlteracao(LocalDatetUtil.currentDateTime());
        livro.setIdEditora(new EditoraEntity(dto.getIdEditora()));
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setPreco(dto.getPreco());
        livro.setDataPublicacao(LocalDatetUtil.converterStringToLocalDate(dto.getDataPublicacao()));

        List<LivroAutorEntity> livroAutores = new ArrayList<>();
        dto.getListAutores().forEach(autor -> {
            var entity = new LivroAutorEntity();
            entity.setIdAutor(new AutorEntity(autor.getId()));
            entity.setIdLivro(livro);
            livroAutores.add(entity);
        });
        livro.setLivroAutorEntityCollection(livroAutores);

        List<LivroCriticaEntity> livroCriticas = new ArrayList<>();
        dto.getListCriticas().forEach(critica -> {
            var entity = new LivroCriticaEntity();
            entity.setIdLivro(livro);
            entity.setNome(critica.getNome());
            entity.setCritica(critica.getCritica());
            livroCriticas.add(entity);
        });
        livro.setLivroCriticaEntityCollection(livroCriticas);

        return livro;

    }

    public LivroEntity update(LivroEntity livro, LivroDto dto) {

        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setPreco(dto.getPreco());
        livro.setDataCadastro(LocalDatetUtil.currentDateTime());
        livro.setDataAlteracao(LocalDatetUtil.currentDateTime());
        livro.setIdEditora(new EditoraEntity(dto.getIdEditora()));

        return livro;

    }

    public static LivroDto converterDto(LivroEntity entity) {

        var dto = new LivroDto();

        dto.setId(entity.getId());
        dto.setIsbn(entity.getIsbn());
        dto.setTitulo(entity.getTitulo());
        dto.setPreco(entity.getPreco());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAlteracao(entity.getDataAlteracao());
        dto.setDataPublicacao(LocalDatetUtil.converterToLocalDate(entity.getDataPublicacao()));

        dto.setEditora(EditoraDto.converterDto(entity.getIdEditora()));

        String precoExtensonew = AppUtil.valueWords(entity.getPreco().doubleValue());
        dto.setPrecoExtenso(precoExtensonew);

        List<AutorDto> listAutor = new ArrayList<>();
        entity.getLivroAutorEntityCollection().forEach(autor -> {
            listAutor.add(AutorDto.converterDto(autor.getIdAutor()));
        });
        dto.setListAutores(listAutor);

        List<CriticaDto> listCritica = new ArrayList<>();
        entity.getLivroCriticaEntityCollection().forEach(critica -> {
            listCritica.add(CriticaDto.converterDto(critica));
        });
        dto.setListCriticas(listCritica);

        var link = new LivroLink();
        dto.add(link.linkView(entity.getId()));
        dto.add(link.linkDelete(entity.getId()));
        dto.add(link.linkUpdate(entity.getId()));

        return dto;
    }

}
