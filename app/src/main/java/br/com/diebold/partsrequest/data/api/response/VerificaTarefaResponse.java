package br.com.diebold.partsrequest.data.api.response;

import java.util.Date;

public class VerificaTarefaResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private boolean success;
    private boolean vTarefa;
    private boolean vSite;
    private boolean vTecnico;
    private int idTarefa;
    private String idSite;
    private int idTecnico;
    private String prodTarefa;
    private int nSerie;
    private Date dtAbertura;
    private Date dtAgendamento;
    private Date prazoAtendimento;
    private Date prazoSolucao;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String catEquipamento;
    private String nmSite;
    private String enderecoSite;
    private int regiaoTecnica;
    private String cliente;

    public boolean isSuccess() {
        return success;
    }

    public boolean setSuccess(boolean success) {
        return this.success = success;
    }

    public boolean isvTarefa() {
        return vTarefa;
    }

    public void setvTarefa(boolean vTarefa) {
        this.vTarefa = vTarefa;
    }

    public boolean isvSite() {
        return vSite;
    }

    public void setvSite(boolean vSite) {
        this.vSite = vSite;
    }

    public boolean isvTecnico() {
        return vTecnico;
    }

    public void setvTecnico(boolean vTecnico) {
        this.vTecnico = vTecnico;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getProdTarefa() {
        return prodTarefa;
    }

    public void setProdTarefa(String prodTarefa) {
        this.prodTarefa = prodTarefa;
    }

    public int getnSerie() {
        return nSerie;
    }

    public void setnSerie(int nSerie) {
        this.nSerie = nSerie;
    }

    public Date getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Date dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public Date getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(Date dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public Date getPrazoAtendimento() {
        return prazoAtendimento;
    }

    public void setPrazoAtendimento(Date prazoAtendimento) {
        this.prazoAtendimento = prazoAtendimento;
    }

    public Date getPrazoSolucao() {
        return prazoSolucao;
    }

    public void setPrazoSolucao(Date prazoSolucao) {
        this.prazoSolucao = prazoSolucao;
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

    public String getCatEquipamento() {
        return catEquipamento;
    }

    public void setCatEquipamento(String catEquipamento) {
        this.catEquipamento = catEquipamento;
    }

    public String getNmSite() {
        return nmSite;
    }

    public void setNmSite(String nmSite) {
        this.nmSite = nmSite;
    }

    public String getEnderecoSite() {
        return enderecoSite;
    }

    public void setEnderecoSite(String enderecoSite) {
        this.enderecoSite = enderecoSite;
    }

    public int getRegiaoTecnica() {
        return regiaoTecnica;
    }

    public void setRegiaoTecnica(int regiaoTecnica) {
        this.regiaoTecnica = regiaoTecnica;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
