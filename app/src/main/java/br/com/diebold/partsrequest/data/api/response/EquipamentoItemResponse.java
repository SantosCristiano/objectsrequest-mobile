package br.com.diebold.partsrequest.data.api.response;

public class EquipamentoItemResponse {
    private String equipamento;

    public EquipamentoItemResponse() {
    }

    public EquipamentoItemResponse(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }
}
