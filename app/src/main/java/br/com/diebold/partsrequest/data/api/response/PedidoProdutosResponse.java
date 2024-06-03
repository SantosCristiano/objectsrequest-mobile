package br.com.diebold.partsrequest.data.api.response;

public class PedidoProdutosResponse {
    private int prodQtd;
    private String prodCodigo;
    private String prodName;
    private String itemCodigo;
    private String itemDescription;
    private boolean itemBom;
    private String dataUltimaAlteracao;

    public PedidoProdutosResponse() {
    }

    public int getProdQtd() {
        return prodQtd;
    }

    public void setProdQtd(int prodQtd) {
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
