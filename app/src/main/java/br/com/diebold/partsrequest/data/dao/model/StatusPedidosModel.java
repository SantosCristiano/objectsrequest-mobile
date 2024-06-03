package br.com.diebold.partsrequest.data.dao.model;

import java.util.Date;

public class StatusPedidosModel  implements IModel {
    private int id;
    private Date data;
    private String usuario;
    private StatusModel status;
    private PedidoModel pedidos;

    public StatusPedidosModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public PedidoModel getPedidos() {
        return pedidos;
    }

    public void setPedidos(PedidoModel pedidos) {
        this.pedidos = pedidos;
    }
}
