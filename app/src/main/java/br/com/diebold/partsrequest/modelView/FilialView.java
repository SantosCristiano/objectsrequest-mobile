package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.FilialResponse;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.IModel;

public class FilialView implements IModel {
    private Integer id;
    private Integer idFilial;
    private String nome;

    public FilialView() {

    }

    public FilialView(FilialResponse filialResponse) {
        if(filialResponse != null) {
            this.idFilial = filialResponse.getId();
            this.nome = filialResponse.getNome();
        }
    }

    public FilialView(FilialModel filialModel) {
        if(filialModel != null) {
            this.id = filialModel.getId();
            this.idFilial = filialModel.getIdFilial();
            this.nome = filialModel.getNome();
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Integer idFilial) {
        this.idFilial = idFilial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
