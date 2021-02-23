package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

/**
 *
 * @author fmatheus
 */
public enum AtributoEnum {

    ID("id"),
    NOME("nome"),
    RAZAO_SOCIAL("razaoSocial"),
    CNPJ("cnpj"),
    TITULO("titulo"),
    ISBN("isbn"),
    ID_PESSOA_FISICA("idPessoaFisica"),
    ID_PESSOA_JURIDICA("idPessoaJuridica"),
    ID_EDITORA("idEditora");

    @Getter
    private final String description;

    AtributoEnum(String description) {
        this.description = description;
    }

}
