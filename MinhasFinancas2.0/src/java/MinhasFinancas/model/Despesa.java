/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.model;

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
@Table(name = "despesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d"),
    @NamedQuery(name = "Despesa.findByIdDespesa", query = "SELECT d FROM Despesa d WHERE d.idDespesa = :idDespesa"),
    @NamedQuery(name = "Despesa.findByDataDespesa", query = "SELECT d FROM Despesa d WHERE d.dataDespesa = :dataDespesa"),
    @NamedQuery(name = "Despesa.findByDescricao", query = "SELECT d FROM Despesa d WHERE d.descricao = :descricao"),
    @NamedQuery(name = "Despesa.findByIntervalo", query = "SELECT d FROM Despesa d WHERE d.intervalo = :intervalo"),
    @NamedQuery(name = "Despesa.findByParcelas", query = "SELECT d FROM Despesa d WHERE d.parcelas = :parcelas"),
    @NamedQuery(name = "Despesa.findByValor", query = "SELECT d FROM Despesa d WHERE d.valor = :valor")})

public class Despesa {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_despesa")
    private Integer idDespesa;

    @Column(name = "data_despesa")
    @Temporal(TemporalType.DATE)
    private Date dataDespesa;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "intervalo")
    private String intervalo;

    @Column(name = "parcelas")
    private Integer parcelas;

    @Column(name = "valor")
    private BigInteger valor;

    @JoinColumn(name = "categoria_despesa_id", referencedColumnName = "id_categoria_despesa")
    @ManyToOne
    private CategoriaDespesa categoriaDespesaId;

    @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id_forma_pagamento")
    @ManyToOne
    private FormaPagamento formaPagamentoId;

    @JoinColumn(name = "moeda_id", referencedColumnName = "id_moeda")
    @ManyToOne
    private Moeda moedaId;

    @JoinColumn(name = "tipo_despesa_id", referencedColumnName = "id_tipo_despesa")
    @ManyToOne
    private TipoDespesa tipoDespesaId;

    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioId;

    public Despesa() {
    }

    public Despesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Date getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
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

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public CategoriaDespesa getCategoriaDespesaId() {
        return categoriaDespesaId;
    }

    public void setCategoriaDespesaId(CategoriaDespesa categoriaDespesaId) {
        this.categoriaDespesaId = categoriaDespesaId;
    }

    public FormaPagamento getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(FormaPagamento formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public Moeda getMoedaId() {
        return moedaId;
    }

    public void setMoedaId(Moeda moedaId) {
        this.moedaId = moedaId;
    }

    public TipoDespesa getTipoDespesaId() {
        return tipoDespesaId;
    }

    public void setTipoDespesaId(TipoDespesa tipoDespesaId) {
        this.tipoDespesaId = tipoDespesaId;
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
        hash += (idDespesa != null ? idDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.idDespesa == null && other.idDespesa != null) || (this.idDespesa != null && !this.idDespesa.equals(other.idDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Despesa[ idDespesa=" + idDespesa + " ]";
    }

}
