package br.com.diebold.partsrequest.modelView;

import java.util.List;

import br.com.diebold.partsrequest.data.dao.model.IModel;

public class TransporteView implements IModel {
    private Integer id;
    private Integer idTransporte;
    private String nome;

    public TransporteView() {
    }

    public TransporteView(Integer id, Integer idTransporte, String nome) {
        this.id = id;
        this.idTransporte = idTransporte;
        this.nome = nome;
    }

    public TransporteView(TransporteView transporteView) {
        this.id = transporteView.getId();
        this.idTransporte = transporteView.getIdTransporte();
        this.nome = transporteView.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
