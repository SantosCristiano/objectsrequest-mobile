package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.TarefaItemView;

public class TarefaModel implements IModel {
    private Integer id;
    private Integer idTarefa;
    private String dataAbertura;
    private String dataLimiteAtendimento;
    private String dataLimiteSolucao;
    private String dataAgendadaParaAtendimento;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String attribute8;

    private SiteModel site = new SiteModel();

    public TarefaModel() {
    }

    public TarefaModel(Integer id, Integer idTarefa, String dataAbertura, String dataLimiteAtendimento, String dataLimiteSolucao, String dataAgendadaParaAtendimento, String descricaoDoEquipamento, String numeroDeSerieDoEquipamento, String attribute8, SiteModel site) {
        this.id = id;
        this.idTarefa = idTarefa;
        this.dataAbertura = dataAbertura;
        this.dataLimiteAtendimento = dataLimiteAtendimento;
        this.dataLimiteSolucao = dataLimiteSolucao;
        this.dataAgendadaParaAtendimento = dataAgendadaParaAtendimento;
        this.descricaoDoEquipamento = descricaoDoEquipamento;
        this.numeroDeSerieDoEquipamento = numeroDeSerieDoEquipamento;
        this.attribute8 = attribute8;
        this.site = site;
    }

    public TarefaModel(TarefaItemView tarefaItemView) {

        if(tarefaItemView != null) {

            this.id = tarefaItemView.getId();
            this.idTarefa = tarefaItemView.getIdTarefa();
            this.dataAbertura = tarefaItemView.getDataAbertura();
            this.dataLimiteAtendimento = tarefaItemView.getDataLimiteAtendimento();
            this.dataLimiteSolucao = tarefaItemView.getDataLimiteSolucao();
            this.dataAgendadaParaAtendimento = tarefaItemView.getDataAgendadaParaAtendimento();
            this.descricaoDoEquipamento = tarefaItemView.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = tarefaItemView.getNumeroDeSerieDoEquipamento();
            this.attribute8 = tarefaItemView.getAttribute8();
            this.site = new SiteModel(tarefaItemView.getSite() );
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

    public SiteModel getSite() {
        return site;
    }

    public void setSite(SiteModel site) {
        this.site = site;
    }
}
