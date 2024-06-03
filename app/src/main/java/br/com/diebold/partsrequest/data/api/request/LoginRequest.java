package br.com.diebold.partsrequest.data.api.request;

public class LoginRequest {
    private String usuario;
    private String senha;
    private String token;

    public LoginRequest(String usuario, String senha, String token) {
        this.usuario = usuario;
        this.senha = senha;
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
