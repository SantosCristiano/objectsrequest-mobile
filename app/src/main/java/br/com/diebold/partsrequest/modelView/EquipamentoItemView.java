package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.EquipamentoItemResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaItemResponse;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaModel;

public class EquipamentoItemView {
    private Integer id;
    private String equipamento;

    public EquipamentoItemView() {
    }

    public EquipamentoItemView(EquipamentoItemResponse equipamentoItemResponse) {
        if(equipamentoItemResponse != null) {
            this.equipamento = equipamentoItemResponse.getEquipamento();
        }
    }

    public EquipamentoItemView(EquipamentoModel equipamentoModel) {
        if(equipamentoModel != null) {
            this.id = equipamentoModel.getId();
            this.equipamento = equipamentoModel.getEquipamento();

        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }
}
