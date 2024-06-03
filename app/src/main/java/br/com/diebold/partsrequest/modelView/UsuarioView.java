package br.com.diebold.partsrequest.modelView;

public class UsuarioView {
    private boolean success;
    private String Mensagem;

    public UsuarioView() {
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
}
