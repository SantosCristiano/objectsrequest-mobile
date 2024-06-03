package br.com.diebold.partsrequest.data.dao.model;

public class TipoPedidoModel  implements IModel {
    private int id;
    private String tipoPedido;

    public TipoPedidoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }
}
