package br.com.diebold.partsrequest.data.api.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioResponse extends ApiErrorResponse implements IApiSuccessResponse {
    private int id;
    private String usuario;
    private String nome;
    private String senha;
    private Date dataInicio;
    private Date dataFim;
    private String dataUltimoAcesso;
    private boolean alteraSenha;
    private List<PerfilResponse> perfis = new ArrayList<PerfilResponse>();
    private List<FilialResponse> filiais = new ArrayList<FilialResponse>();
    private TecnicoResponse tecnico = new TecnicoResponse();
    private boolean success;

    public UsuarioResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(String dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public boolean isAlteraSenha() {
        return alteraSenha;
    }

    public void setAlteraSenha(boolean alteraSenha) {
        this.alteraSenha = alteraSenha;
    }

    public List<PerfilResponse> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilResponse> perfis) {
        this.perfis = perfis;
    }

    public List<FilialResponse> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<FilialResponse> filiais) {
        this.filiais = filiais;
    }

    public TecnicoResponse getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoResponse tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean setSuccess(boolean success) {
        return this.success = success;
    }
}
