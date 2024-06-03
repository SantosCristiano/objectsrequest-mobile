package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.FilialView;
import br.com.diebold.partsrequest.modelView.SiteView;
import br.com.diebold.partsrequest.modelView.TarefaSiteView;

public class TarefaSiteModel implements IModel {
    private Integer id;
    private Integer idTarefa;
    private String idSite;

    public TarefaSiteModel() {

    }

    public TarefaSiteModel(Integer id, Integer idTarefa, String idSite) {
        this.id = id;
        this.idTarefa = idTarefa;
        this.idSite = idSite;
    }

    public TarefaSiteModel(TarefaSiteView tarefaSiteView) {
        this.id = tarefaSiteView.getId();
        this.idTarefa = tarefaSiteView.getIdTarefa();
        this.idSite = tarefaSiteView.getIdSite();
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
}
