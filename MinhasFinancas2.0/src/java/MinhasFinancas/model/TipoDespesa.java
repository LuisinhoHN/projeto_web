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
@Table(name = "tipo_despesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDespesa.findAll", query = "SELECT t FROM TipoDespesa t"),
    @NamedQuery(name = "TipoDespesa.findByIdTipoDespesa", query = "SELECT t FROM TipoDespesa t WHERE t.idTipoDespesa = :idTipoDespesa"),
    @NamedQuery(name = "TipoDespesa.findByNome", query = "SELECT t FROM TipoDespesa t WHERE t.nome = :nome")})
public class TipoDespesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_despesa")
    private Integer idTipoDespesa;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "tipoDespesaId")
    private List<Despesa> despesaList;

    public TipoDespesa() {
    }

    public TipoDespesa(Integer idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }

    public Integer getIdTipoDespesa() {
        return idTipoDespesa;
    }

    public void setIdTipoDespesa(Integer idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDespesa != null ? idTipoDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDespesa)) {
            return false;
        }
        TipoDespesa other = (TipoDespesa) object;
        if ((this.idTipoDespesa == null && other.idTipoDespesa != null) || (this.idTipoDespesa != null && !this.idTipoDespesa.equals(other.idTipoDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.TipoDespesa[ idTipoDespesa=" + idTipoDespesa + " ]";
    }
    
}
