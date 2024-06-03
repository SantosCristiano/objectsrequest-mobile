package br.com.diebold.partsrequest.data.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Body", strict = false)
public class FullBOMRequestBody {
    public FullBOMRequestBody(FullBOMRequestData fullBomRequestData) {
        this.fullBomRequestData = fullBomRequestData;
    }

    @Element(name = "GetBomFullPaginationJson", required = false)
    private FullBOMRequestData fullBomRequestData;

    public FullBOMRequestData getFullBomRequestData() {
        return fullBomRequestData;
    }

    public void setBomRequestData(FullBOMRequestData fullBOMRequestData) {
        this.fullBomRequestData = fullBomRequestData;
    }
}
