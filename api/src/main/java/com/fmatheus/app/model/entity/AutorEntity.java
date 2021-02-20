package com.fmatheus.app.model.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "autor", catalog = "livraria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"id_pessoa_fisica"})})

public class AutorEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAutor")
    private Collection<LivroAutorEntity> livroAutorEntityCollection;

    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private PessoaFisicaEntity idPessoaFisica;

    public AutorEntity() {
    }

    public AutorEntity(Integer id) {
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

    @XmlTransient
    public Collection<LivroAutorEntity> getLivroAutorEntityCollection() {
        return livroAutorEntityCollection;
    }

    public void setLivroAutorEntityCollection(Collection<LivroAutorEntity> livroAutorEntityCollection) {
        this.livroAutorEntityCollection = livroAutorEntityCollection;
    }

    public PessoaFisicaEntity getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(PessoaFisicaEntity idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
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
        if (!(object instanceof AutorEntity)) {
            return false;
        }
        AutorEntity other = (AutorEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.fmatheus.app.model.entity.AutorEntity[ id=" + id + " ]";
    }

}
