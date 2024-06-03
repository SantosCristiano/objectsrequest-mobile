package br.com.diebold.partsrequest.data.api.response;

import java.util.List;

public class TransporteResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private Integer id;
    private String nome;

    public TransporteResponse() {
    }

    public TransporteResponse(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
