package br.com.diebold.partsrequest.modelView;

import java.util.List;

public class PedidoView {
    private boolean success;
    private String Mensagem;
    private Integer idPedidoWS;

    public Integer getIdPedidoWS() {
        return idPedidoWS;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedidoWS = idPedido;
    }

    private List<PedidoItemView> pedidos;

    public List<PedidoItemView> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoItemView> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean logado) {
        this.success = logado;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
}
