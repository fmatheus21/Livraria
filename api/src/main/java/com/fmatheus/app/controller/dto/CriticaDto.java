package com.fmatheus.app.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.model.entity.LivroCriticaEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"nome", "critica"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Critica", description = "API Critica")
public class CriticaDto extends RepresentationModel<CriticaDto> {
 
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    @JsonProperty("nome")
    @ApiModelProperty(notes = "Nome do Crítico", required = true)
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10)
    @JsonProperty("critica")
    @ApiModelProperty(notes = "Crítica", required = true)
    private String critica;

    public static CriticaDto converterDto(LivroCriticaEntity entity) {

        var dto = new CriticaDto();
        dto.setNome(entity.getNome());
        dto.setNome(entity.getNome());

        return dto;
    }    
   

}
