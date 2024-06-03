package br.com.diebold.partsrequest.data.api.response;

public class SiteResponse {
    private String id;
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
    private int regiaoTecnica;
    private FilialResponse filial = new FilialResponse();

    public SiteResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getRegiaoTecnica() {
        return regiaoTecnica;
    }

    public void setRegiaoTecnica(int regiaoTecnica) {
        this.regiaoTecnica = regiaoTecnica;
    }

    public FilialResponse getFilial() {
        return filial;
    }

    public void setFilial(FilialResponse filial) {
        this.filial = filial;
    }
}
