package br.com.diebold.partsrequest.data.dao.model;

import java.util.Date;

public class TecnicoModel  implements IModel {
    private int id;
    private int categoria;
    private Date ultimoLogin;
    private Date fimVigencia;
    private Date inicioVigencia;
    private String nome;
    private String senha;
    private String status;
    private int latitude;
    private int longitude;
    private String usuario;
    private boolean mobile;
    private Date geolocalizacao;
    private String tipoGeolocalizacao;
    private int oracle;
    private boolean localResidente;
    private PedidoModel pedidos;

    public TecnicoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
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

    public String getTipoGeolocalizacao() {
        return tipoGeolocalizacao;
    }

    public void setTipoGeolocalizacao(String tipoGeolocalizacao) {
        this.tipoGeolocalizacao = tipoGeolocalizacao;
    }

    public int getOracle() {
        return oracle;
    }

    public void setOracle(int oracle) {
        this.oracle = oracle;
    }

    public boolean isLocalResidente() {
        return localResidente;
    }

    public void setLocalResidente(boolean localResidente) {
        this.localResidente = localResidente;
    }

    public PedidoModel getPedidos() {
        return pedidos;
    }

    public void setPedidos(PedidoModel pedidos) {
        this.pedidos = pedidos;
    }
}
