package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.TarefaItemResponse;
import br.com.diebold.partsrequest.data.dao.model.TarefaModel;

public class TarefaItemView {
    private Integer id;
    private Integer idTarefa;
    private String dataAbertura;
    private String dataLimiteAtendimento;
    private String dataLimiteSolucao;
    private String dataAgendadaParaAtendimento;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String attribute8;
    private SiteView site;

    public TarefaItemView(TarefaItemResponse tarefaItemResponse) {
        if(tarefaItemResponse != null) {

            this.idTarefa = tarefaItemResponse.getId();
            this.dataAbertura = tarefaItemResponse.getDataAbertura();
            this.dataLimiteAtendimento = tarefaItemResponse.getDataLimiteAtendimento();
            this.dataLimiteSolucao = tarefaItemResponse.getDataLimiteSolucao();
            this.dataAgendadaParaAtendimento = tarefaItemResponse.getDataAgendadaParaAtendimento();
            this.descricaoDoEquipamento = tarefaItemResponse.getDescricaoDoEquipamento();
            this.descricaoDoEquipamento = tarefaItemResponse.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = tarefaItemResponse.getNumeroDeSerieDoEquipamento();
            this.attribute8 = tarefaItemResponse.getAttribute8();

            if(tarefaItemResponse.getSite() != null) {
                this.site = new SiteView (tarefaItemResponse.getSite());
            }
        }
    }

    public TarefaItemView(TarefaModel tarefaModel) {
        if(tarefaModel != null) {

            this.idTarefa = tarefaModel.getIdTarefa();
            this.dataAbertura = tarefaModel.getDataAbertura();
            this.dataLimiteAtendimento = tarefaModel.getDataLimiteAtendimento();
            this.dataLimiteSolucao = tarefaModel.getDataLimiteSolucao();
            this.dataAgendadaParaAtendimento = tarefaModel.getDataAgendadaParaAtendimento();
            this.descricaoDoEquipamento = tarefaModel.getDescricaoDoEquipamento();
            this.descricaoDoEquipamento = tarefaModel.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = tarefaModel.getNumeroDeSerieDoEquipamento();
            this.attribute8 = tarefaModel.getAttribute8();

            if(tarefaModel.getSite() != null) {
                this.site = new SiteView (tarefaModel.getSite());
            }
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

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataLimiteAtendimento() {
        return dataLimiteAtendimento;
    }

    public void setDataLimiteAtendimento(String dataLimiteAtendimento) {
        this.dataLimiteAtendimento = dataLimiteAtendimento;
    }

    public String getDataLimiteSolucao() {
        return dataLimiteSolucao;
    }

    public void setDataLimiteSolucao(String dataLimiteSolucao) {
        this.dataLimiteSolucao = dataLimiteSolucao;
    }

    public String getDataAgendadaParaAtendimento() {
        return dataAgendadaParaAtendimento;
    }

    public void setDataAgendadaParaAtendimento(String dataAgendadaParaAtendimento) {
        this.dataAgendadaParaAtendimento = dataAgendadaParaAtendimento;
    }

    public String getDescricaoDoEquipamento() {
        return descricaoDoEquipamento;
    }

    public void setDescricaoDoEquipamento(String descricaoDoEquipamento) {
        this.descricaoDoEquipamento = descricaoDoEquipamento;
    }

    public String getNumeroDeSerieDoEquipamento() {
        return numeroDeSerieDoEquipamento;
    }

    public void setNumeroDeSerieDoEquipamento(String numeroDeSerieDoEquipamento) {
        this.numeroDeSerieDoEquipamento = numeroDeSerieDoEquipamento;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public SiteView  getSite() {
        return site;
    }

    public void setSite(SiteView site) {
        this.site = site;
    }
}
