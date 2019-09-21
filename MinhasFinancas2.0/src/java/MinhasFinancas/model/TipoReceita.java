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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tipo_receita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoReceita.findAll", query = "SELECT t FROM TipoReceita t"),
    @NamedQuery(name = "TipoReceita.findByIdTipoReceita", query = "SELECT t FROM TipoReceita t WHERE t.idTipoReceita = :idTipoReceita"),
    @NamedQuery(name = "TipoReceita.findByNome", query = "SELECT t FROM TipoReceita t WHERE t.nome = :nome")})
public class TipoReceita implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_receita")
    private Integer idTipoReceita;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "tipoReceitaId")
    private List<Receita> receitaList;

    public TipoReceita() {
    }

    public TipoReceita(Integer idTipoReceita) {
        this.idTipoReceita = idTipoReceita;
    }

    public Integer getIdTipoReceita() {
        return idTipoReceita;
    }

    public void setIdTipoReceita(Integer idTipoReceita) {
        this.idTipoReceita = idTipoReceita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Receita> getReceitaList() {
        return receitaList;
    }

    public void setReceitaList(List<Receita> receitaList) {
        this.receitaList = receitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReceita != null ? idTipoReceita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReceita)) {
            return false;
        }
        TipoReceita other = (TipoReceita) object;
        if ((this.idTipoReceita == null && other.idTipoReceita != null) || (this.idTipoReceita != null && !this.idTipoReceita.equals(other.idTipoReceita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.TipoReceita[ idTipoReceita=" + idTipoReceita + " ]";
    }
    
}
