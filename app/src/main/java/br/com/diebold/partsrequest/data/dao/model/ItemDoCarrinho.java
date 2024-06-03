package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.PedidoProdutosView;

public class ItemDoCarrinho implements IModel {
    private Integer id;
    private Integer idPedido;
    private Integer idProduto;
    private Integer prodQtd;
    private String prodCodigo;

    public ItemDoCarrinho() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getProdQtd() {
        return prodQtd;
    }

    public void setProdQtd(Integer prodQtd) {
        this.prodQtd = prodQtd;
    }

    public String getProdCodigo() {
        return prodCodigo;
    }

    public void setProdCodigo(String prodCodigo) {
        this.prodCodigo = prodCodigo;
    }

}
