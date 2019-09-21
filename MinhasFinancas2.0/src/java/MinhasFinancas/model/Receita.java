/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "receita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receita.findAll", query = "SELECT r FROM Receita r"),
    @NamedQuery(name = "Receita.findByIdReceita", query = "SELECT r FROM Receita r WHERE r.idReceita = :idReceita"),
    @NamedQuery(name = "Receita.findByDataReceita", query = "SELECT r FROM Receita r WHERE r.dataReceita = :dataReceita"),
    @NamedQuery(name = "Receita.findByDescricao", query = "SELECT r FROM Receita r WHERE r.descricao = :descricao"),
    @NamedQuery(name = "Receita.findByIntervalo", query = "SELECT r FROM Receita r WHERE r.intervalo = :intervalo"),
    @NamedQuery(name = "Receita.findByValor", query = "SELECT r FROM Receita r WHERE r.valor = :valor")})
public class Receita implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_receita")
    private Integer idReceita;
    
    @Column(name = "data_receita")
    @Temporal(TemporalType.DATE)
    private Date dataReceita;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "intervalo")
    private String intervalo;
    
    @Column(name = "valor")
    private BigInteger valor;
    
    @JoinColumn(name = "moeda_id", referencedColumnName = "id_moeda")
    @ManyToOne
    private Moeda moedaId;
    
    @JoinColumn(name = "tipo_receita_id", referencedColumnName = "id_tipo_receita")
    @ManyToOne
    private TipoReceita tipoReceitaId;
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioId;

    public Receita() {
    }

    public Receita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Date getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(Date dataReceita) {
        this.dataReceita = dataReceita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public Moeda getMoedaId() {
        return moedaId;
    }

    public void setMoedaId(Moeda moedaId) {
        this.moedaId = moedaId;
    }

    public TipoReceita getTipoReceitaId() {
        return tipoReceitaId;
    }

    public void setTipoReceitaId(TipoReceita tipoReceitaId) {
        this.tipoReceitaId = tipoReceitaId;
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
        hash += (idReceita != null ? idReceita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receita)) {
            return false;
        }
        Receita other = (Receita) object;
        if ((this.idReceita == null && other.idReceita != null) || (this.idReceita != null && !this.idReceita.equals(other.idReceita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Receita[ idReceita=" + idReceita + " ]";
    }
    
}
