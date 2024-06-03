package br.com.diebold.partsrequest.data.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiBOMErrorResponse implements Serializable {
    @SerializedName("MensagemErro")
    @Expose
    private String mensagemErro;

    @SerializedName("DescricaoErro")
    @Expose
    private String descricaoErro;

    public ApiBOMErrorResponse() {
    }

    public void setError(ApiBOMErrorResponse aber){
        this.mensagemErro = aber.mensagemErro;
        this.descricaoErro = aber.descricaoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }
}
