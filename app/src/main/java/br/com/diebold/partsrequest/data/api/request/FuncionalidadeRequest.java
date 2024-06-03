package br.com.diebold.partsrequest.data.api.request;

public class FuncionalidadeRequest {
    private int id;
    private String funcionalidade;
    private String nmFuncionalidade;
    private String pagina;

    public FuncionalidadeRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public String getNmFuncionalidade() {
        return nmFuncionalidade;
    }

    public void setNmFuncionalidade(String nmFuncionalidade) {
        this.nmFuncionalidade = nmFuncionalidade;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
}
