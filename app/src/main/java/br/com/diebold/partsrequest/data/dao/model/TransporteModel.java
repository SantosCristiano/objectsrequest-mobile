package br.com.diebold.partsrequest.data.dao.model;

public class TransporteModel implements IModel {
    private Integer id;
    private Integer idTransporte;
    private String nome;

    public TransporteModel() {
    }

    public TransporteModel(Integer id, Integer idTransporte, String nome) {
        this.id = id;
        this.idTransporte = idTransporte;
        this.nome = nome;
    }

    public TransporteModel(TransporteModel transporteModel) {
        this.id = transporteModel.getId();
        this.idTransporte = transporteModel.getIdTransporte();
        this.nome = transporteModel.getNome();
    }

    public TransporteModel(Integer idTransporte, String nomeTransporte) {
        this.idTransporte = idTransporte;
        this.nome = nomeTransporte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
