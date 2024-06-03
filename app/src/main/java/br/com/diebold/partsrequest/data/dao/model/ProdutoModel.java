package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.SiteView;

public class ProdutoModel  implements IModel {
    private Integer id;
    private Integer idProduto;
    private Integer destroyFlag;
    private String itemCode;
    private String itemDescription;
    private String itemFamily;
    private String productCode;
    private String productName;
    private String attribute1;
    private PedidoProdutosModel pedidosProdutos;
    private Integer qtd;

    public ProdutoModel() {

    }

    public ProdutoModel(Integer id, Integer idProduto, Integer destroyFlag, String itemCode, String itemDescription, String itemFamily, String productCode, String productName, String attribute1, PedidoProdutosModel pedidosProdutos, Integer qtd) {
        this.id = id;
        this.idProduto = idProduto;
        this.destroyFlag = destroyFlag;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemFamily = itemFamily;
        this.productCode = productCode;
        this.productName = productName;
        this.attribute1 = attribute1;
        this.pedidosProdutos = pedidosProdutos;
        this.qtd = qtd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getDestroyFlag() {
        return destroyFlag;
    }

    public void setDestroyFlag(Integer destroyFlag) {
        this.destroyFlag = destroyFlag;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(String itemFamily) {
        this.itemFamily = itemFamily;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public PedidoProdutosModel getPedidosProdutos() {
        return pedidosProdutos;
    }

    public void setPedidosProdutos(PedidoProdutosModel pedidosProdutos) {
        this.pedidosProdutos = pedidosProdutos;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
