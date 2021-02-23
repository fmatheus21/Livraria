package com.fmatheus.app.model.entity;

import com.fmatheus.app.controller.util.AppUtil;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "livro_critica", catalog = "livraria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})

public class LivroCriticaEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 70)
    @Column(name = "nome", nullable = false, length = 70)
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "critica", nullable = false, length = 16777215)
    private String critica;

    @JoinColumn(name = "id_livro", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LivroEntity idLivro;

    public LivroCriticaEntity() {
    }

    public LivroCriticaEntity(Integer id) {
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

    public String getNome() {
        if (nome != null) {
            return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(nome));
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(nome));
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
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
        if (!(object instanceof LivroCriticaEntity)) {
            return false;
        }
        LivroCriticaEntity other = (LivroCriticaEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.fmatheus.app.model.entity.LivroCriticaEntity[ id=" + id + " ]";
    }

}
