package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.SiteResponse;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.IModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;

public class SiteView implements IModel {
    private Integer id;
    private String idSite;
    private double latitude;
    private double longitude;
    private String bairro;
    private String cep;
    private String cidade;
    private String endereco;
    private String estado;
    private String pais;
    private String site;
    private String status;
    private String telefone;
    private String cliente;
    private Integer regiaoTecnica;
    private FilialView filial;

    public SiteView() {

    }

    public SiteView(SiteResponse siteResponse) {
        if (siteResponse != null) {
            this.endereco = siteResponse.getEndereco();
            this.bairro = siteResponse.getBairro();
            this.cep = siteResponse.getCep();
            this.cidade = siteResponse.getCidade();
            this.cliente = siteResponse.getCliente();
            this.estado = siteResponse.getEstado();
            this.pais = siteResponse.getPais();
            this.idSite = siteResponse.getId();
            this.latitude = siteResponse.getLatitude();
            this.longitude = siteResponse.getLongitude();
            this.regiaoTecnica = siteResponse.getRegiaoTecnica();
            this.site = siteResponse.getSite();
            this.status = siteResponse.getStatus();
            this.telefone = siteResponse.getTelefone();

            if (siteResponse.getFilial() != null) {
                this.filial = new FilialView(siteResponse.getFilial());
            }

        }
    }

    public SiteView(SiteModel siteModel) {
        if (siteModel != null) {
            this.id = siteModel.getId();
            this.idSite = siteModel.getIdSite();
            this.endereco = siteModel.getEndereco();
            this.bairro = siteModel.getBairro();
            this.cep = siteModel.getCep();
            this.cidade = siteModel.getCidade();
            this.cliente = siteModel.getCliente();
            this.estado = siteModel.getEstado();
            this.pais = siteModel.getPais();
            this.latitude = siteModel.getLatitude();
            this.longitude = siteModel.getLongitude();
            this.regiaoTecnica = siteModel.getRegiaoTecnica();
            this.site = siteModel.getSite();
            this.status = siteModel.getStatus();
            this.telefone = siteModel.getTelefone();

            if (siteModel.getFilial() != null) {
                this.filial = new FilialView(siteModel.getFilial());
            }

        }
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getRegiaoTecnica() {
        return regiaoTecnica;
    }

    public void setRegiaoTecnica(Integer regiaoTecnica) {
        this.regiaoTecnica = regiaoTecnica;
    }

    public FilialView getFilial() {
        return filial;
    }

    public void setFilial(FilialView filial) {
        this.filial = filial;
    }
}
