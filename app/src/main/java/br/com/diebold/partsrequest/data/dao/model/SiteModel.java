package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.SiteView;

public class SiteModel  implements IModel {
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
    private FilialModel filial;

    public SiteModel() {

    }
    public SiteModel(SiteView siteView) {
        this.id = siteView.getId();
        this.idSite = siteView.getIdSite();
        this.latitude = siteView.getLatitude();
        this.longitude = siteView.getLongitude();
        this.bairro = siteView.getBairro();
        this.cep = siteView.getCep();
        this.cidade = siteView.getCidade();
        this.endereco = siteView.getEndereco();
        this.estado =siteView.getEstado();
        this.pais =siteView.getPais();
        this.site = siteView.getSite();
        this.status = siteView.getStatus();
        this.telefone = siteView.getTelefone();
        this.cliente = siteView.getCliente();
        this.regiaoTecnica = siteView.getRegiaoTecnica();
        this.filial = new FilialModel( siteView.getFilial());

    }

    public SiteModel(Integer id, String idSite, double latitude, double longitude, String bairro, String cep, String cidade, String endereco, String estado, String pais, String site, String status, String telefone, String cliente, Integer regiaoTecnica, FilialModel filial) {
        this.id = id;
        this.idSite = idSite;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.endereco = endereco;
        this.estado = estado;
        this.pais = pais;
        this.site = site;
        this.status = status;
        this.telefone = telefone;
        this.cliente = cliente;
        this.regiaoTecnica = regiaoTecnica;
        this.filial = filial;
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

    public FilialModel getFilial() {
        return filial;
    }

    public void setFilial(FilialModel filial) {
        this.filial = filial;
    }
}
