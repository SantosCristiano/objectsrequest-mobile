package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.FilialResponse;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.IModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;

public class TarefaFilialView implements IModel {
    private Integer id;
    private Integer idTarefa;
    private String idSite;
    private Integer idFilial;

    public TarefaFilialView() {

    }

    public TarefaFilialView(FilialResponse filialResponse) {
        if(filialResponse != null) {
            this.idFilial = filialResponse.getId();
        }
    }

    public TarefaFilialView(TarefaFilialModel tarefaFilialModel) {
        if(tarefaFilialModel != null) {
            this.id = tarefaFilialModel.getId();
            this.idTarefa = tarefaFilialModel.getIdTarefa();
            this.idSite = tarefaFilialModel.getIdSite();
            this.idFilial = tarefaFilialModel.getIdFilial();
        }
    }

    public TarefaFilialView(int idTarefa, String idSite, FilialView filialView) {
        if(filialView != null) {
            this.id = filialView.getId();
            this.idTarefa = idTarefa;
            this.idSite = idSite;
            this.idFilial = filialView.getIdFilial();
        }
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
