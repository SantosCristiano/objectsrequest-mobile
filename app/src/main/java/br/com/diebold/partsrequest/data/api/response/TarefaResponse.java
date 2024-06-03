package br.com.diebold.partsrequest.data.api.response;

import java.util.List;

public class TarefaResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private List<TarefaItemResponse> tarefas;

    public List<TarefaItemResponse> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaItemResponse> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean setSuccess(boolean success) {
        return this.success = success;
    }
}
