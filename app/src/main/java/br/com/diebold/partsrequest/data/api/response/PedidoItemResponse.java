package br.com.diebold.partsrequest.data.api.response;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.request.TransporteRequest;

public class PedidoItemResponse {
    private Integer idPedido;
    private String stPstatusData;
    private String stPstatusUsuario;
    private String stPstatusStatus;
    private String nomeLocalizacao;
    private String tipoPedido;
    private Integer idTarefa;
    private String prodTarefa;
    private Integer nSerie;
    private String dtAbertura;
    private String dtAgendamento;
    private String prazoAtendimento;
    private String prazoSolucao;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String catEquipamento;
    private String idSite;
    private String nmSite;
    private String enderecoSite;
    private Integer regiaoTecnica;
    private String nomeFilial;
    private String cliente;
    private Integer idTecnico;
    private String nmTecnico;
    private List<PedidoProdutosResponse> produto = new ArrayList<PedidoProdutosResponse>();
    private String eaLogradouro;
    private String eaCep;
    private String eaBairro;
    private String eaCidade;
    private String eaNum;
    private String dtEntrega;
    private String nomeEntregador;
    private String telefoneEntregador;
    private String observacao;
    private boolean buscaPedido;
    private String dataUltimaAlteracao;
    private TransporteResponse transporte;

    public PedidoItemResponse() {
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getStPstatusData() {
        return stPstatusData;
    }

    public void setStPstatusData(String stPstatusData) {
        this.stPstatusData = stPstatusData;
    }

    public String getStPstatusUsuario() {
        return stPstatusUsuario;
    }

    public void setStPstatusUsuario(String stPstatusUsuario) {
        this.stPstatusUsuario = stPstatusUsuario;
    }

    public String getStPstatusStatus() {
        return stPstatusStatus;
    }

    public void setStPstatusStatus(String stPstatusStatus) {
        this.stPstatusStatus = stPstatusStatus;
    }

    public String getNomeLocalizacao() {
        return nomeLocalizacao;
    }

    public void setNomeLocalizacao(String nomeLocalizacao) {
        this.nomeLocalizacao = nomeLocalizacao;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getProdTarefa() {
        return prodTarefa;
    }

    public void setProdTarefa(String prodTarefa) {
        this.prodTarefa = prodTarefa;
    }

    public Integer getnSerie() {
        return nSerie;
    }

    public void setnSerie(Integer nSerie) {
        this.nSerie = nSerie;
    }

    public String getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(String dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public String getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(String dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public String getPrazoAtendimento() {
        return prazoAtendimento;
    }

    public void setPrazoAtendimento(String prazoAtendimento) {
        this.prazoAtendimento = prazoAtendimento;
    }

    public String getPrazoSolucao() {
        return prazoSolucao;
    }

    public void setPrazoSolucao(String prazoSolucao) {
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

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
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

    public Integer getRegiaoTecnica() {
        return regiaoTecnica;
    }

    public void setRegiaoTecnica(Integer regiaoTecnica) {
        this.regiaoTecnica = regiaoTecnica;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public void setNomeFilial(String nomeFilial) {
        this.nomeFilial = nomeFilial;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNmTecnico() {
        return nmTecnico;
    }

    public void setNmTecnico(String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }

    public List<PedidoProdutosResponse> getProduto() {
        return produto;
    }

    public void setProduto(List<PedidoProdutosResponse> produto) {
        this.produto = produto;
    }

    public String getEaLogradouro() {
        return eaLogradouro;
    }

    public void setEaLogradouro(String eaLogradouro) {
        this.eaLogradouro = eaLogradouro;
    }

    public String getEaCep() {
        return eaCep;
    }

    public void setEaCep(String eaCep) {
        this.eaCep = eaCep;
    }

    public String getEaBairro() {
        return eaBairro;
    }

    public void setEaBairro(String eaBairro) {
        this.eaBairro = eaBairro;
    }

    public String getEaCidade() {
        return eaCidade;
    }

    public void setEaCidade(String eaCidade) {
        this.eaCidade = eaCidade;
    }

    public String getEaNum() {
        return eaNum;
    }

    public void setEaNum(String eaNum) {
        this.eaNum = eaNum;
    }

    public String getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(String dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }

    public String getTelefoneEntregador() {
        return telefoneEntregador;
    }

    public void setTelefoneEntregador(String telefoneEntregador) {
        this.telefoneEntregador = telefoneEntregador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isBuscaPedido() {
        return buscaPedido;
    }

    public void setBuscaPedido(boolean buscaPedido) {
        this.buscaPedido = buscaPedido;
    }

    public String getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public TransporteResponse getTransporte() {
        return transporte;
    }

    public void setTransporte(TransporteResponse transporte) {
        this.transporte = transporte;
    }
}
