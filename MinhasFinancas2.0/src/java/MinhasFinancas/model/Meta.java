/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "meta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m"),
    @NamedQuery(name = "Meta.findByIdMeta", query = "SELECT m FROM Meta m WHERE m.idMeta = :idMeta"),
    @NamedQuery(name = "Meta.findByInvestimentoMensal", query = "SELECT m FROM Meta m WHERE m.investimentoMensal = :investimentoMensal"),
    @NamedQuery(name = "Meta.findByNome", query = "SELECT m FROM Meta m WHERE m.nome = :nome"),
    @NamedQuery(name = "Meta.findByParcelasPrevistasTotal", query = "SELECT m FROM Meta m WHERE m.parcelasPrevistasTotal = :parcelasPrevistasTotal"),
    @NamedQuery(name = "Meta.findByPercentualPoupanca", query = "SELECT m FROM Meta m WHERE m.percentualPoupanca = :percentualPoupanca"),
    @NamedQuery(name = "Meta.findBySubtotalParcelas", query = "SELECT m FROM Meta m WHERE m.subtotalParcelas = :subtotalParcelas"),
    @NamedQuery(name = "Meta.findBySubtotalValor", query = "SELECT m FROM Meta m WHERE m.subtotalValor = :subtotalValor"),
    @NamedQuery(name = "Meta.findByTipo", query = "SELECT m FROM Meta m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Meta.findByValorTotal", query = "SELECT m FROM Meta m WHERE m.valorTotal = :valorTotal")})
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_meta")
    private Integer idMeta;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "investimento_mensal")
    private Double investimentoMensal;

    @Column(name = "nome")
    private String nome;

    @Column(name = "parcelas_previstas_total")
    private Integer parcelasPrevistasTotal;

    @Column(name = "percentual_poupanca")
    private Double percentualPoupanca;

    @Column(name = "subtotal_parcelas")
    private Integer subtotalParcelas;

    @Column(name = "subtotal_valor")
    private Double subtotalValor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valor_total")
    private Double valorTotal;

    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioId;

    public Meta() {
    }

    public Meta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Double getInvestimentoMensal() {
        return investimentoMensal;
    }

    public void setInvestimentoMensal(Double investimentoMensal) {
        this.investimentoMensal = investimentoMensal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getParcelasPrevistasTotal() {
        return parcelasPrevistasTotal;
    }

    public void setParcelasPrevistasTotal(Integer parcelasPrevistasTotal) {
        this.parcelasPrevistasTotal = parcelasPrevistasTotal;
    }

    public Double getPercentualPoupanca() {
        return percentualPoupanca;
    }

    public void setPercentualPoupanca(Double percentualPoupanca) {
        this.percentualPoupanca = percentualPoupanca;
    }

    public Integer getSubtotalParcelas() {
        return subtotalParcelas;
    }

    public void setSubtotalParcelas(Integer subtotalParcelas) {
        this.subtotalParcelas = subtotalParcelas;
    }

    public Double getSubtotalValor() {
        return subtotalValor;
    }

    public void setSubtotalValor(Double subtotalValor) {
        this.subtotalValor = subtotalValor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
        hash += (idMeta != null ? idMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.idMeta == null && other.idMeta != null) || (this.idMeta != null && !this.idMeta.equals(other.idMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Meta[ idMeta=" + idMeta + " ]";
    }
    
}
