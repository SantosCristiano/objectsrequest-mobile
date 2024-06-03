package br.com.diebold.partsrequest.modelView;

import java.util.List;

import br.com.diebold.partsrequest.data.dao.model.IModel;

public class EquipamentoBomView implements IModel {
    private boolean success;
    private String Mensagem;
    private List<EquipamentoBomItemView> equipamentosBom;

    public EquipamentoBomView() {

    }

    public List<EquipamentoBomItemView> getEquipamentosBom() {
        return equipamentosBom;
    }

    public void setEquipamentosBom(List<EquipamentoBomItemView> equipamentosBom) {
        this.equipamentosBom = equipamentosBom;
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
