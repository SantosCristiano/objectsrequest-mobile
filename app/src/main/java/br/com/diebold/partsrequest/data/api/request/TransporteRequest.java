package br.com.diebold.partsrequest.data.api.request;

import br.com.diebold.partsrequest.data.dao.model.TransporteModel;

public class TransporteRequest {
    private Integer id;
    private String nome;

    public TransporteRequest() {
    }

    public TransporteRequest(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TransporteRequest(TransporteModel transporteModel) {
        this.id = transporteModel.getId();
        this.nome = transporteModel.getNome();
    }

    public TransporteRequest(TransporteRequest transporteRequest) {
        this.id = transporteRequest.getId();
        this.nome = transporteRequest.getNome();
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
}
