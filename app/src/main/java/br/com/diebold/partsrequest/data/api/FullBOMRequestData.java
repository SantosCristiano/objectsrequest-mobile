package br.com.diebold.partsrequest.data.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "GetBomFullPaginationJson", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class FullBOMRequestData {

    public FullBOMRequestData(Integer numPagina, String dataCorte) {
        this.numPagina = numPagina;
        this.dataCorte = dataCorte;
    }

    @Element(name = "NumPagina", required = false)
    private Integer numPagina;

    @Element(name = "DataCorte", required = false)
    private String dataCorte;

    public Integer getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(Integer numPagina) {
        this.numPagina = numPagina;
    }

    public String getDataCorte() {
        return dataCorte;
    }

    public void setDataCorte(String dataCorte) {
        this.dataCorte = dataCorte;
    }
}
