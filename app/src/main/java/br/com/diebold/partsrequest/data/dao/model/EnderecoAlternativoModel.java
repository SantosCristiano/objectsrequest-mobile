package br.com.diebold.partsrequest.data.dao.model;

public class EnderecoAlternativoModel implements IModel {
    private int id;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String numero;
    private PedidoModel pedidos;

    public EnderecoAlternativoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PedidoModel getPedidos() {
        return pedidos;
    }

    public void setPedidos(PedidoModel pedidos) {
        this.pedidos = pedidos;
    }
}
