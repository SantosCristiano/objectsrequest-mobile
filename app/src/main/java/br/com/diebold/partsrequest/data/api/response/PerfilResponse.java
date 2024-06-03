package br.com.diebold.partsrequest.data.api.response;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.request.FuncionalidadeRequest;

public class PerfilResponse {
    private int id;
    private String nome;
    private List<FuncionalidadeRequest> funcionalidades = new ArrayList<FuncionalidadeRequest>();

    public PerfilResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<FuncionalidadeRequest> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<FuncionalidadeRequest> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }
}
