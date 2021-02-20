package com.fmatheus.app.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "pessoa", catalog = "livraria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
public class PessoaEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoa")
    private PessoaFisicaEntity pessoaFisicaEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoa")
    private PessoaJuridicaEntity pessoaJuridicaEntity;

    public PessoaEntity() {
    }

    public PessoaEntity(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public PessoaFisicaEntity getPessoaFisicaEntity() {
        return pessoaFisicaEntity;
    }

    public void setPessoaFisicaEntity(PessoaFisicaEntity pessoaFisicaEntity) {
        this.pessoaFisicaEntity = pessoaFisicaEntity;
    }

    public PessoaJuridicaEntity getPessoaJuridicaEntity() {
        return pessoaJuridicaEntity;
    }

    public void setPessoaJuridicaEntity(PessoaJuridicaEntity pessoaJuridicaEntity) {
        this.pessoaJuridicaEntity = pessoaJuridicaEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaEntity)) {
            return false;
        }
        PessoaEntity other = (PessoaEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.fmatheus.app.model.entity.PessoaEntity[ id=" + id + " ]";
    }

}
