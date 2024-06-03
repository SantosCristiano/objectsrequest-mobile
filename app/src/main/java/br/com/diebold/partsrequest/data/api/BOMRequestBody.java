package br.com.diebold.partsrequest.data.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Body", strict = false)
public class BOMRequestBody {
    public BOMRequestBody(BOMRequestData bomRequestData) {
        this.bomRequestData = bomRequestData;
    }

    @Element(name = "GetBom",required = false)
    private BOMRequestData bomRequestData;

    public BOMRequestData getBomRequestData() {
        return bomRequestData;
    }

    public void setBomRequestData(BOMRequestData bomRequestData) {
        this.bomRequestData = bomRequestData;
    }
}
