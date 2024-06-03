package br.com.diebold.partsrequest.data.api.response;

import java.util.List;

public class EquipamentoBomResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private List<EquipamentoBomItemResponse> equipamentosBom;

    public EquipamentoBomResponse() {
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean setSuccess(boolean success) {
        return this.success = success;
    }

    public List<EquipamentoBomItemResponse> getEquipamentosBom() {
        return equipamentosBom;
    }

    public void setEquipamentosBom(List<EquipamentoBomItemResponse> equipamentosBom) {
        this.equipamentosBom = equipamentosBom;
    }
}
