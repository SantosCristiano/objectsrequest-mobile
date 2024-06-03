package br.com.diebold.partsrequest.data.dao.model;

public class LocalizacaoModel  implements IModel {
    private int id;
    private String nomeLocalizacao;

    public LocalizacaoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLocalizacao() {
        return nomeLocalizacao;
    }

    public void setNomeLocalizacao(String nomeLocalizacao) {
        this.nomeLocalizacao = nomeLocalizacao;
    }
}
