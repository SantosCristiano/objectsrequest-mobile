package br.com.diebold.partsrequest.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ApiErrorResponse {


    public LoginResponse() {
    }

    @SerializedName("sucesso")
    @Expose
    private Boolean sucesso;

    private String token;


    public Boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
