package br.com.diebold.partsrequest.data.api.response;

import java.util.Date;

public class TecnicoResponse {
    private int idTecnico;
    private int categoria;
    private Date ultimoLogin;
    private Date fimVigencia;
    private Date inicioVigencia;
    private String nome;
    private String senha;
    private String status;
    private double latitude;
    private double longitude;
    private String usuario;
    private boolean mobile;
    private Date geolocalizacao;
    private char tipoGeolocalizacao;
    private int oracle;
    private String localResidente;

    public TecnicoResponse() {
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public Date getGeolocalizacao() {
        return geolocalizacao;
    }

    public void setGeolocalizacao(Date geolocalizacao) {
        this.geolocalizacao = geolocalizacao;
    }

    public char getTipoGeolocalizacao() {
        return tipoGeolocalizacao;
    }

    public void setTipoGeolocalizacao(char tipoGeolocalizacao) {
        this.tipoGeolocalizacao = tipoGeolocalizacao;
    }

    public int getOracle() {
        return oracle;
    }

    public void setOracle(int oracle) {
        this.oracle = oracle;
    }

    public String getLocalResidente() {
        return localResidente;
    }

    public void setLocalResidente(String localResidente) {
        this.localResidente = localResidente;
    }
}
