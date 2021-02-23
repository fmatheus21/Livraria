package com.fmatheus.app.model.persistence.repository;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
public class RepositoryFilter {

    @Getter
    @Setter
    private String isbn;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cnpj;

    @Getter
    @Setter
    private String razaoSocial;

    @Getter
    @Setter
    private String editora;

}
