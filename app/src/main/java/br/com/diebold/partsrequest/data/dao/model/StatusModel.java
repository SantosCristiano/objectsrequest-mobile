package br.com.diebold.partsrequest.data.dao.model;

public class StatusModel  implements IModel {
    private int id;
    private String status;
    private StatusPedidosModel statusPedidos;

    public StatusModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusPedidosModel getStatusPedidos() {
        return statusPedidos;
    }

    public void setStatusPedidos(StatusPedidosModel statusPedidos) {
        this.statusPedidos = statusPedidos;
    }
}
