package br.com.diebold.partsrequest.modelView;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.response.TarefaResponse;

public class TarefaView {
    private boolean success;
    private String Mensagem;
    private List<TarefaItemView> tarefa;

    public  TarefaView(){
        tarefa = new ArrayList<>();
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

    public List<TarefaItemView> getTarefa() {
        return tarefa;
    }

    public void setTarefa(List<TarefaItemView> tarefa) {
        this.tarefa = tarefa;
    }
}
