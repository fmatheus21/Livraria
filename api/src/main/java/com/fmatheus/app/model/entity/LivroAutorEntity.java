package com.fmatheus.app.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "livro_autor", catalog = "livraria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})

public class LivroAutorEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "id_autor", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AutorEntity idAutor;

    @JoinColumn(name = "id_livro", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LivroEntity idLivro;

    public LivroAutorEntity() {
    }

    public LivroAutorEntity(Integer id) {
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

    public AutorEntity getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(AutorEntity idAutor) {
        this.idAutor = idAutor;
    }

    public LivroEntity getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(LivroEntity idLivro) {
        this.idLivro = idLivro;
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
        if (!(object instanceof LivroAutorEntity)) {
            return false;
        }
        LivroAutorEntity other = (LivroAutorEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.fmatheus.app.model.entity.LivroAutorEntity[ id=" + id + " ]";
    }

}
