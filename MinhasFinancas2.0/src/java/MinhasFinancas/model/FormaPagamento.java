/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.model;

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
@Table(name = "forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idFormaPagamento = :idFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByDescricao", query = "SELECT f FROM FormaPagamento f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FormaPagamento.findByNome", query = "SELECT f FROM FormaPagamento f WHERE f.nome = :nome")})

public class FormaPagamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioId;

    @OneToMany(mappedBy = "formaPagamentoId")
    private List<Despesa> despesaList;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
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

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.FormaPagamento[ idFormaPagamento=" + idFormaPagamento + " ]";
    }

}
