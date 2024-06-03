package br.com.diebold.partsrequest.modelView;

import java.util.List;

import br.com.diebold.partsrequest.data.api.response.EquipamentoResponse;
import br.com.diebold.partsrequest.data.api.response.FilialResponse;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoModel;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.IModel;

public class EquipamentoView implements IModel {
    private boolean success;
    private String Mensagem;
    private List<EquipamentoItemView> equipamentos;

    public EquipamentoView() {

    }

    public List<EquipamentoItemView> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoItemView> equipamentos) {
        this.equipamentos = equipamentos;
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
