package br.com.diebold.partsrequest.data.dao.model;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.request.TransporteRequest;
import br.com.diebold.partsrequest.modelView.PedidoItemView;

public class PedidoModel  implements IModel {

    private Integer id;
    private Integer idPedidoWS;
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
    private List<PedidoProdutosModel> produto = new ArrayList<PedidoProdutosModel>();
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
    private TransporteModel transporte;

    public PedidoModel(Integer id, Integer idPedidoWS, String stPstatusData, String stPstatusUsuario, String stPstatusStatus,
                       String nomeLocalizacao, String tipoPedido, Integer idTarefa, String prodTarefa, Integer nSerie,
                       String dtAbertura, String dtAgendamento, String prazoAtendimento, String prazoSolucao, String descricaoDoEquipamento,
                       String numeroDeSerieDoEquipamento, String catEquipamento, String idSite, String nmSite, String enderecoSite, Integer regiaoTecnica,
                       String nomeFilial, String cliente, Integer idTecnico, String nmTecnico, List<PedidoProdutosModel> produto, String eaLogradouro,
                       String eaCep, String eaBairro, String eaCidade, String eaNum, String dtEntrega, String nomeEntregador, String telefoneEntregador,
                       String observacao, boolean buscaPedido, String dataUltimaAlteracao, TransporteModel  transporte) {
        this.id = id;
        this.idPedidoWS = idPedidoWS;
        this.stPstatusData = stPstatusData;
        this.stPstatusUsuario = stPstatusUsuario;
        this.stPstatusStatus = stPstatusStatus;
        this.nomeLocalizacao = nomeLocalizacao;
        this.tipoPedido = tipoPedido;
        this.idTarefa = idTarefa;
        this.prodTarefa = prodTarefa;
        this.nSerie = nSerie;
        this.dtAbertura = dtAbertura;
        this.dtAgendamento = dtAgendamento;
        this.prazoAtendimento = prazoAtendimento;
        this.prazoSolucao = prazoSolucao;
        this.descricaoDoEquipamento = descricaoDoEquipamento;
        this.numeroDeSerieDoEquipamento = numeroDeSerieDoEquipamento;
        this.catEquipamento = catEquipamento;
        this.idSite = idSite;
        this.nmSite = nmSite;
        this.enderecoSite = enderecoSite;
        this.regiaoTecnica = regiaoTecnica;
        this.nomeFilial = nomeFilial;
        this.cliente = cliente;
        this.idTecnico = idTecnico;
        this.nmTecnico = nmTecnico;
        this.produto = produto;
        this.eaLogradouro = eaLogradouro;
        this.eaCep = eaCep;
        this.eaBairro = eaBairro;
        this.eaCidade = eaCidade;
        this.eaNum = eaNum;
        this.dtEntrega = dtEntrega;
        this.nomeEntregador = nomeEntregador;
        this.telefoneEntregador = telefoneEntregador;
        this.observacao = observacao;
        this.buscaPedido = buscaPedido;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.transporte = transporte;
    }

    public PedidoModel(PedidoItemView pedidoItemView) {

        if(pedidoItemView != null) {
            this.id = pedidoItemView.getId();
            this.idPedidoWS = pedidoItemView.getIdPedidoWS();
            this.stPstatusData = pedidoItemView.getStPstatusData();
            this.stPstatusUsuario = pedidoItemView.getStPstatusUsuario();
            this.stPstatusStatus = pedidoItemView.getStPstatusStatus();
            this.nomeLocalizacao = pedidoItemView.getNomeLocalizacao();
            this.tipoPedido = pedidoItemView.getTipoPedido();
            this.idTarefa = pedidoItemView.getIdTarefa();
            this.prodTarefa = pedidoItemView.getProdTarefa();
            this.nSerie = pedidoItemView.getnSerie();
            this.dtAbertura = pedidoItemView.getDtAbertura();
            this.dtAgendamento = pedidoItemView.getDtAgendamento();
            this.prazoAtendimento = pedidoItemView.getPrazoAtendimento();
            this.prazoSolucao = pedidoItemView.getPrazoSolucao();
            this.descricaoDoEquipamento = pedidoItemView.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = pedidoItemView.getNumeroDeSerieDoEquipamento();
            this.catEquipamento = pedidoItemView.getCatEquipamento();
            this.idSite = pedidoItemView.getIdSite();
            this.nmSite = pedidoItemView.getNmSite();
            this.enderecoSite = pedidoItemView.getEnderecoSite();
            this.regiaoTecnica = pedidoItemView.getRegiaoTecnica();
            this.nomeFilial = pedidoItemView.getNomeFilial();
            this.cliente = pedidoItemView.getCliente();
            this.idTecnico = pedidoItemView.getIdTecnico();
            this.nmTecnico = pedidoItemView.getNmTecnico();
            if(pedidoItemView.getProduto() != null) {

                this.produto = new ArrayList<PedidoProdutosModel>();

                for (int i = 0; i < pedidoItemView.getProduto().size(); i++) {
                    PedidoProdutosModel pedidoProdutos = new PedidoProdutosModel();

                    pedidoProdutos.setProdCodigo(pedidoItemView.getProduto().get(i).getProdCodigo());
                    pedidoProdutos.setProdQtd(pedidoItemView.getProduto().get(i).getProdQtd());

                    this.produto.add(pedidoProdutos);
                }
            }

            this.eaLogradouro = pedidoItemView.getEaLogradouro();
            this.eaCep = pedidoItemView.getEaCep();
            this.eaBairro = pedidoItemView.getEaBairro();
            this.eaCidade = pedidoItemView.getEaCidade();
            this.eaNum = pedidoItemView.getEaNum();
            this.dtEntrega = pedidoItemView.getDtEntrega();
            this.nomeEntregador = pedidoItemView.getNomeEntregador();
            this.telefoneEntregador = pedidoItemView.getTelefoneEntregador();
            this.observacao = pedidoItemView.getObservacao();
            this.buscaPedido = pedidoItemView.isBuscaPedido();
            this.dataUltimaAlteracao = pedidoItemView.getDataUltimaAlteracao();
            if(pedidoItemView.getTransporte() != null) {
                TransporteModel transporteModel = new TransporteModel();
                transporteModel.setIdTransporte(pedidoItemView.getTransporte().getIdTransporte());
                transporteModel.setId(pedidoItemView.getTransporte().getIdTransporte());
                transporteModel.setNome(pedidoItemView.getTransporte().getNome());

                this.transporte = transporteModel;
            }
        }


    }

    public PedidoModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedidoWS() {
        return idPedidoWS;
    }

    public void setIdPedidoWS(Integer idPedidoWS) {
        this.idPedidoWS = idPedidoWS;
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

    public List<PedidoProdutosModel> getProduto() {
        return produto;
    }

    public void setProduto(List<PedidoProdutosModel> produto) {
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

    public TransporteModel getTransporte() {
        return transporte;
    }

    public void setTransporte(TransporteModel transporte) {
        this.transporte = transporte;
    }
}
