package br.com.diebold.partsrequest.data.api.response;

public class TarefaItemResponse {
    private int id;
    private String dataAbertura;
    private String dataLimiteAtendimento;
    private String dataLimiteSolucao;
    private String dataAgendadaParaAtendimento;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String attribute8;

    private SiteResponse site = new SiteResponse();

    public TarefaItemResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SiteResponse getSite() {
        return site;
    }

    public void setSite(SiteResponse site) {
        this.site = site;
    }
}
