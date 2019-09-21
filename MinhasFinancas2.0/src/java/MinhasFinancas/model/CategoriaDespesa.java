/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categoria_despesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaDespesa.findAll", query = "SELECT c FROM CategoriaDespesa c"),
    @NamedQuery(name = "CategoriaDespesa.findByIdCategoriaDespesa", query = "SELECT c FROM CategoriaDespesa c WHERE c.idCategoriaDespesa = :idCategoriaDespesa"),
    @NamedQuery(name = "CategoriaDespesa.findByDescricao", query = "SELECT c FROM CategoriaDespesa c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "CategoriaDespesa.findByNome", query = "SELECT c FROM CategoriaDespesa c WHERE c.nome = :nome")})
public class CategoriaDespesa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_despesa")
    private Integer idCategoriaDespesa;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "categoriaDespesaId")
    private List<Despesa> despesaList;
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioId;

    public CategoriaDespesa() {
    }

    public CategoriaDespesa(Integer idCategoriaDespesa) {
        this.idCategoriaDespesa = idCategoriaDespesa;
    }

    public Integer getIdCategoriaDespesa() {
        return idCategoriaDespesa;
    }

    public void setIdCategoriaDespesa(Integer idCategoriaDespesa) {
        this.idCategoriaDespesa = idCategoriaDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Despesa> getDespesaList() {
        return despesaList;
    }

    public void setDespesaList(List<Despesa> despesaList) {
        this.despesaList = despesaList;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaDespesa != null ? idCategoriaDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaDespesa)) {
            return false;
        }
        CategoriaDespesa other = (CategoriaDespesa) object;
        if ((this.idCategoriaDespesa == null && other.idCategoriaDespesa != null) || (this.idCategoriaDespesa != null && !this.idCategoriaDespesa.equals(other.idCategoriaDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.CategoriaDespesa[ idCategoriaDespesa=" + idCategoriaDespesa + " ]";
    }
    
    
}
