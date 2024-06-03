    package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.PedidoProdutosView;

public class PedidoProdutosModel implements IModel {
    private Integer id;
    private Integer idPedidoDB;
    private Integer idProduto;
    private Integer prodQtd;
    private String prodCodigo;
    private String prodName;
    private String itemCodigo;
    private String itemDescription;
    private boolean itemBom;
    private String dataUltimaAlteracao;

    public PedidoProdutosModel() {
    }

    public PedidoProdutosModel(PedidoProdutosView pedidoProdutosView) {
        if(pedidoProdutosView != null) {
            this.id = pedidoProdutosView.getId();
            this.idPedidoDB = pedidoProdutosView.getIdPedidoDB();
            this.idProduto = pedidoProdutosView.getIdProduto();
            this.prodQtd = pedidoProdutosView.getProdQtd();
            this.prodCodigo = pedidoProdutosView.getProdCodigo();
            this.prodName = pedidoProdutosView.getProdName();
            this.itemCodigo = pedidoProdutosView.getItemCodigo();
            this.itemDescription = pedidoProdutosView.getItemDescription();
            this.itemBom = pedidoProdutosView.isItemBom();
            this.dataUltimaAlteracao = pedidoProdutosView.getDataUltimaAlteracao();
        }
    }

    public PedidoProdutosModel(Integer id, Integer idPedidoDB, Integer idProduto, Integer prodQtd, String prodCodigo, String prodName, String itemCodigo, String itemDescription, boolean itemBom, String dataUltimaAlteracao) {
        this.id = id;
        this.idPedidoDB = idPedidoDB;
        this.idProduto = idProduto;
        this.prodQtd = prodQtd;
        this.prodCodigo = prodCodigo;
        this.prodName = prodName;
        this.itemCodigo = itemCodigo;
        this.itemDescription = itemDescription;
        this.itemBom = itemBom;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public PedidoProdutosModel(PedidoProdutosModel pedidoProdutosModel) {
        if(pedidoProdutosModel != null) {
            this.id = pedidoProdutosModel.getId();
            this.idPedidoDB = pedidoProdutosModel.getIdPedidoDB();
            this.idProduto = pedidoProdutosModel.getIdProduto();
            this.prodQtd = pedidoProdutosModel.getProdQtd();
            this.prodCodigo = pedidoProdutosModel.getProdCodigo();
            this.prodName = pedidoProdutosModel.getProdName();
            this.itemCodigo = pedidoProdutosModel.getItemCodigo();
            this.itemDescription = pedidoProdutosModel.getItemDescription();
            this.itemBom = pedidoProdutosModel.isItemBom();
            this.dataUltimaAlteracao = pedidoProdutosModel.getDataUltimaAlteracao();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedidoDB() {
        return idPedidoDB;
    }

    public void setIdPedidoDB(Integer idPedidoDB) {
        this.idPedidoDB = idPedidoDB;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getItemCodigo() {
        return itemCodigo;
    }

    public void setItemCodigo(String itemCodigo) {
        this.itemCodigo = itemCodigo;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isItemBom() {
        return itemBom;
    }

    public void setItemBom(boolean itemBom) {
        this.itemBom = itemBom;
    }

    public String getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
