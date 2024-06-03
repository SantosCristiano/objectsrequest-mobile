package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.FilialView;

public class FilialModel  implements IModel {
    private Integer id;
    private Integer idFilial;
    private String nome;

    public FilialModel() {

    }

    public FilialModel(Integer id, Integer idFilial, String nome) {
        this.id = id;
        this.idFilial = idFilial;
        this.nome = nome;
    }

    public FilialModel(FilialView filialView) {
        this.id = filialView.getId();
        this.idFilial = filialView.getIdFilial();
        this.nome = filialView.getNome();
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
