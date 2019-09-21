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
@Table(name = "moeda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moeda.findAll", query = "SELECT m FROM Moeda m"),
    @NamedQuery(name = "Moeda.findByIdMoeda", query = "SELECT m FROM Moeda m WHERE m.idMoeda = :idMoeda"),
    @NamedQuery(name = "Moeda.findByMoedaparaRS", query = "SELECT m FROM Moeda m WHERE m.moedaparaRS = :moedaparaRS"),
    @NamedQuery(name = "Moeda.findByNome", query = "SELECT m FROM Moeda m WHERE m.nome = :nome"),
    @NamedQuery(name = "Moeda.findByRSparamoeda", query = "SELECT m FROM Moeda m WHERE m.rSparamoeda = :rSparamoeda")})

public class Moeda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_moeda")
    private Integer idMoeda;
    
// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "moeda_para_RS")
    private Double moedaparaRS;

    @Column(name = "nome")
    private String nome;

    @Column(name = "RS_para_moeda")
    private Double rSparamoeda;

    @OneToMany(mappedBy = "moedaId")
    private List<Receita> receitaList;

    @OneToMany(mappedBy = "moedaId")
    private List<Despesa> despesaList;

    @OneToMany(mappedBy = "moedaId")
    private List<Usuario> usuarioList;

    public Moeda() {
    }

    public Moeda(Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public Integer getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public Double getMoedaparaRS() {
        return moedaparaRS;
    }

    public void setMoedaparaRS(Double moedaparaRS) {
        this.moedaparaRS = moedaparaRS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getRSparamoeda() {
        return rSparamoeda;
    }

    public void setRSparamoeda(Double rSparamoeda) {
        this.rSparamoeda = rSparamoeda;
    }

    @XmlTransient
    public List<Receita> getReceitaList() {
        return receitaList;
    }

    public void setReceitaList(List<Receita> receitaList) {
        this.receitaList = receitaList;
    }

    @XmlTransient
    public List<Despesa> getDespesaList() {
        return despesaList;
    }

    public void setDespesaList(List<Despesa> despesaList) {
        this.despesaList = despesaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoeda != null ? idMoeda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moeda)) {
            return false;
        }
        Moeda other = (Moeda) object;
        if ((this.idMoeda == null && other.idMoeda != null) || (this.idMoeda != null && !this.idMoeda.equals(other.idMoeda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Moeda[ idMoeda=" + idMoeda + " ]";
    }
    
}
