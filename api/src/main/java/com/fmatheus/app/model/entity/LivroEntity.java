package com.fmatheus.app.model.entity;

import com.fmatheus.app.controller.util.AppUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "livro", catalog = "livraria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"isbn"})})

public class LivroEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 15)
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo", nullable = false, length = 45)
    private String titulo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "preco", nullable = false, precision = 8, scale = 2)
    private BigDecimal preco;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivro")
    private Collection<LivroAutorEntity> livroAutorEntityCollection;

    @JoinColumn(name = "id_editora", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EditoraEntity idEditora;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivro")
    private Collection<LivroCriticaEntity> livroCriticaEntityCollection;

    public LivroEntity() {
    }

    public LivroEntity(Integer id) {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        if (titulo != null) {
            return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeAccent(titulo));
        }
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = AppUtil.convertAllUppercaseCharacters(AppUtil.removeAccent(titulo));
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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

    public EditoraEntity getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(EditoraEntity idEditora) {
        this.idEditora = idEditora;
    }

    @XmlTransient
    public Collection<LivroCriticaEntity> getLivroCriticaEntityCollection() {
        return livroCriticaEntityCollection;
    }

    public void setLivroCriticaEntityCollection(Collection<LivroCriticaEntity> livroCriticaEntityCollection) {
        this.livroCriticaEntityCollection = livroCriticaEntityCollection;
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
        if (!(object instanceof LivroEntity)) {
            return false;
        }
        LivroEntity other = (LivroEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.fmatheus.app.model.entity.LivroEntity[ id=" + id + " ]";
    }

}
