package br.com.diebold.partsrequest.data.dao.model;

public class ModalidadeEntregaModel  implements IModel {
    private int id;
    private String modalidade;
    private String observacao;

    public ModalidadeEntregaModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
