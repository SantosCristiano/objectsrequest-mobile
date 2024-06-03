package br.com.diebold.partsrequest.data.api.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private int idPedido;
    private List<PedidoItemResponse> pedidos;

    public List<PedidoItemResponse> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoItemResponse> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean setSuccess(boolean success) {
        return this.success = success;
    }

//    public int getIdPedido() {
//        return idPedido;
//    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPedidoWS() {
        return idPedido;
    }

//    public void setIdPedidoWS(int idPedido) {
//        this.idPedido = idPedido;
//    }
}
