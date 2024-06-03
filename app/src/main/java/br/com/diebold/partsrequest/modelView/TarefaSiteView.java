package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.FilialResponse;
import br.com.diebold.partsrequest.data.api.response.SiteResponse;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.IModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaSiteModel;

public class TarefaSiteView implements IModel {
    private Integer id;
    private Integer idTarefa;
    private String idSite;

    public TarefaSiteView() {

    }

    public TarefaSiteView(SiteResponse siteResponse) {
        if(siteResponse != null) {
            this.idSite = siteResponse.getId();
        }
    }

    public TarefaSiteView(TarefaSiteModel tarefaSiteModel) {
        if(tarefaSiteModel != null) {
            this.id = tarefaSiteModel.getId();
            this.idTarefa = tarefaSiteModel.getIdTarefa();
            this.idSite = tarefaSiteModel.getIdSite();
        }
    }

    public TarefaSiteView(int idTarefa, SiteView siteView) {
        if(siteView != null) {
            this.id = siteView.getId();
            this.idTarefa = idTarefa;
            this.idSite = siteView.getIdSite();
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
}
