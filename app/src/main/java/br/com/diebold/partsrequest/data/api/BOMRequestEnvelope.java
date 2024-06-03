package br.com.diebold.partsrequest.data.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")
})
public class BOMRequestEnvelope {
    public BOMRequestEnvelope(BOMRequestBody body) {
        this.body = body;
    }

    @Element(name = "soap12:Body", required = false)
    private BOMRequestBody body;

    public BOMRequestBody getBody() {
        return body;
    }

    public void setBody(BOMRequestBody body) {
        this.body = body;
    }
}
