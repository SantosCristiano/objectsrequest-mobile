package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.EquipamentoItemView;

public class EquipamentoModel implements IModel {
    private Integer id;
    private String equipamento;

    public EquipamentoModel() {

    }

    public EquipamentoModel(Integer id, String equipamento) {
        this.id = id;
        this.equipamento = equipamento;
    }

    public EquipamentoModel(EquipamentoItemView equipamentoItemView) {
        if(equipamentoItemView != null) {
            this.id = equipamentoItemView.getId();
            this.equipamento = equipamentoItemView.getEquipamento();
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
