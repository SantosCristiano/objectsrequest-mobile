package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.FilialView;
import br.com.diebold.partsrequest.modelView.TarefaFilialView;

public class TarefaFilialModel implements IModel {
    private Integer id;
    private Integer idTarefa;
    private String idSite;
    private Integer idFilial;

    public TarefaFilialModel() {

    }

    public TarefaFilialModel(Integer id, Integer idTarefa, String idSite, Integer idFilial) {
        this.id = id;
        this.idTarefa = idFilial;
        this.idSite = idSite;
        this.idFilial = idFilial;
    }

    public TarefaFilialModel(TarefaFilialView tarefaFilialView) {
        this.id = tarefaFilialView.getId();
        this.idTarefa = tarefaFilialView.getIdTarefa();
        this.idSite = tarefaFilialView.getIdSite();
        this.idFilial = tarefaFilialView.getIdFilial();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public Integer getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Integer idFilial) {
        this.idFilial = idFilial;
    }
}
