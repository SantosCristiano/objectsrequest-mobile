package br.com.diebold.partsrequest.modelView;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.response.PedidoItemResponse;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;

public class PedidoItemView {

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
    private List<PedidoProdutosView> produto = new ArrayList<PedidoProdutosView>();
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
    private TransporteView transporte;

    public PedidoItemView(){

    }
    public PedidoItemView(PedidoItemResponse pedidoItemResponse) {

        if(pedidoItemResponse != null) {

            this.idPedidoWS = pedidoItemResponse.getIdPedido();
            this.stPstatusData = pedidoItemResponse.getStPstatusData();
            this.stPstatusUsuario = pedidoItemResponse.getStPstatusUsuario();
            this.stPstatusStatus = pedidoItemResponse.getStPstatusStatus();
            this.nomeLocalizacao = pedidoItemResponse.getNomeLocalizacao();
            this.tipoPedido = pedidoItemResponse.getTipoPedido();
            this.idTarefa = pedidoItemResponse.getIdTarefa();
            this.prodTarefa = pedidoItemResponse.getProdTarefa();
            this.nSerie = pedidoItemResponse.getnSerie();
            this.dtAbertura = pedidoItemResponse.getDtAbertura();
            this.dtAgendamento = pedidoItemResponse.getDtAgendamento();
            this.prazoAtendimento = pedidoItemResponse.getPrazoAtendimento();
            this.prazoSolucao = pedidoItemResponse.getPrazoSolucao();
            this.descricaoDoEquipamento = pedidoItemResponse.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = pedidoItemResponse.getNumeroDeSerieDoEquipamento();
            this.catEquipamento = pedidoItemResponse.getCatEquipamento();
            this.idSite = pedidoItemResponse.getIdSite();
            this.nmSite = pedidoItemResponse.getNmSite();
            this.enderecoSite = pedidoItemResponse.getEnderecoSite();
            this.regiaoTecnica = pedidoItemResponse.getRegiaoTecnica();
            this.nomeFilial = pedidoItemResponse.getNomeFilial();
            if(pedidoItemResponse.getProduto() != null) {

                this.produto = new ArrayList<PedidoProdutosView>();

                for (int i = 0; i < pedidoItemResponse.getProduto().size(); i++) {
                    PedidoProdutosView pedidoProdutos = new PedidoProdutosView();

                    pedidoProdutos.setProdCodigo(pedidoItemResponse.getProduto().get(i).getProdCodigo());
                    pedidoProdutos.setProdQtd(pedidoItemResponse.getProduto().get(i).getProdQtd());
                    pedidoProdutos.setProdName(pedidoItemResponse.getProduto().get(i).getProdName());
                    pedidoProdutos.setItemCodigo(pedidoItemResponse.getProduto().get(i).getItemCodigo());
                    pedidoProdutos.setItemDescription(pedidoItemResponse.getProduto().get(i).getItemDescription());
                    pedidoProdutos.setItemBom(pedidoItemResponse.getProduto().get(i).isItemBom());

                    this.produto.add(pedidoProdutos);
                }
            }
            this.cliente = pedidoItemResponse.getCliente();
            this.idTecnico = pedidoItemResponse.getIdTecnico();
            this.nmTecnico = pedidoItemResponse.getNmTecnico();
            this.eaLogradouro = pedidoItemResponse.getEaLogradouro();
            this.eaCep = pedidoItemResponse.getEaCep();
            this.eaBairro = pedidoItemResponse.getEaBairro();
            this.eaCidade = pedidoItemResponse.getEaCidade();
            this.eaNum = pedidoItemResponse.getEaNum();
            this.dtEntrega = pedidoItemResponse.getDtEntrega();
            this.nomeEntregador = pedidoItemResponse.getNomeEntregador();
            this.telefoneEntregador = pedidoItemResponse.getTelefoneEntregador();
            this.observacao = pedidoItemResponse.getObservacao();
            this.buscaPedido = pedidoItemResponse.isBuscaPedido();
            this.dataUltimaAlteracao = pedidoItemResponse.getDataUltimaAlteracao();
            if(pedidoItemResponse.getTransporte() != null) {
                TransporteView transporteView = new TransporteView();
                transporteView.setIdTransporte(pedidoItemResponse.getTransporte().getId());
                transporteView.setId(pedidoItemResponse.getTransporte().getId());
                transporteView.setNome(pedidoItemResponse.getTransporte().getNome());

                this.transporte = transporteView;
            }
        }


    }

    public PedidoItemView(PedidoModel pedidoModel) {

        if(pedidoModel != null) {
            this.id = pedidoModel.getId();
            this.idPedidoWS = pedidoModel.getIdPedidoWS();
            this.stPstatusData = pedidoModel.getStPstatusData();
            this.stPstatusUsuario = pedidoModel.getStPstatusUsuario();
            this.stPstatusStatus = pedidoModel.getStPstatusStatus();
            this.nomeLocalizacao = pedidoModel.getNomeLocalizacao();
            this.tipoPedido = pedidoModel.getTipoPedido();
            this.idTarefa = pedidoModel.getIdTarefa();
            this.prodTarefa = pedidoModel.getProdTarefa();
            this.nSerie = pedidoModel.getnSerie();
            this.dtAbertura = pedidoModel.getDtAbertura();
            this.dtAgendamento = pedidoModel.getDtAgendamento();
            this.prazoAtendimento = pedidoModel.getPrazoAtendimento();
            this.prazoSolucao = pedidoModel.getPrazoSolucao();
            this.descricaoDoEquipamento = pedidoModel.getDescricaoDoEquipamento();
            this.numeroDeSerieDoEquipamento = pedidoModel.getNumeroDeSerieDoEquipamento();
            this.catEquipamento = pedidoModel.getCatEquipamento();
            this.idSite = pedidoModel.getIdSite();
            this.nmSite = pedidoModel.getNmSite();
            this.enderecoSite = pedidoModel.getEnderecoSite();
            this.regiaoTecnica = pedidoModel.getRegiaoTecnica();
            this.nomeFilial = pedidoModel.getNomeFilial();
            if(pedidoModel.getProduto() != null) {

                this.produto = new ArrayList<PedidoProdutosView>();

                for (int i = 0; i < pedidoModel.getProduto().size(); i++) {
                    PedidoProdutosView pedidoProdutos = new PedidoProdutosView();

                    pedidoProdutos.setProdCodigo(pedidoModel.getProduto().get(i).getProdCodigo());
                    pedidoProdutos.setProdQtd(pedidoModel.getProduto().get(i).getProdQtd());
                    pedidoProdutos.setProdName(pedidoModel.getProduto().get(i).getProdName());
                    pedidoProdutos.setItemCodigo(pedidoModel.getProduto().get(i).getItemCodigo());
                    pedidoProdutos.setItemDescription(pedidoModel.getProduto().get(i).getItemDescription());
                    pedidoProdutos.setItemBom(pedidoModel.getProduto().get(i).isItemBom());

                    this.produto.add(pedidoProdutos);
                }
            }
            this.cliente = pedidoModel.getCliente();
            this.idTecnico = pedidoModel.getIdTecnico();
            this.nmTecnico = pedidoModel.getNmTecnico();
            this.eaLogradouro = pedidoModel.getEaLogradouro();
            this.eaCep = pedidoModel.getEaCep();
            this.eaBairro = pedidoModel.getEaBairro();
            this.eaCidade = pedidoModel.getEaCidade();
            this.eaNum = pedidoModel.getEaNum();
            this.dtEntrega = pedidoModel.getDtEntrega();
            this.nomeEntregador = pedidoModel.getNomeEntregador();
            this.telefoneEntregador = pedidoModel.getTelefoneEntregador();
            this.observacao = pedidoModel.getObservacao();
            this.buscaPedido = pedidoModel.isBuscaPedido();
            this.dataUltimaAlteracao = pedidoModel.getDataUltimaAlteracao();
            if(pedidoModel.getTransporte() != null) {
                TransporteView transporteView = new TransporteView();
                transporteView.setIdTransporte(pedidoModel.getTransporte().getIdTransporte());
                transporteView.setId(pedidoModel.getTransporte().getIdTransporte());
                transporteView.setNome(pedidoModel.getTransporte().getNome());

                this.transporte = transporteView;
            }
        }
    }

    public Integer getIdPedidoWS() {
        return idPedidoWS;
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

    public String getProdTarefa() {
        return prodTarefa;
    }

    public void setProdTarefa(String prodTarefa) {
        this.prodTarefa = prodTarefa;
    }

    public Integer getnSerie() {
        return nSerie;
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

    public String getNmTecnico() {
        return nmTecnico;
    }

    public void setNmTecnico(String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }

    public List<PedidoProdutosView> getProduto() {
        return produto;
    }

    public void setProduto(List<PedidoProdutosView> produto) {
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdPedidoWS(Integer idPedidoWS) {
        this.idPedidoWS = idPedidoWS;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public void setnSerie(Integer nSerie) {
        this.nSerie = nSerie;
    }

    public void setRegiaoTecnica(Integer regiaoTecnica) {
        this.regiaoTecnica = regiaoTecnica;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
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

    public TransporteView getTransporte() {
        return transporte;
    }

    public void setTransporte(TransporteView transporte) {
        this.transporte = transporte;
    }
}
