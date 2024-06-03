package br.com.diebold.partsrequest.data.api.response;

import java.util.List;

public class EquipamentoResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private List<EquipamentoItemResponse> equipamentos;

    public EquipamentoResponse() {
    }

    public List<EquipamentoItemResponse> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoItemResponse> equipamentos) {
        this.equipamentos = equipamentos;
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
