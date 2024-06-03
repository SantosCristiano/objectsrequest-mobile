package br.com.diebold.partsrequest.data.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "GetBom", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class BOMRequestData {

    public BOMRequestData(String productCode) {
        this.productCode = productCode;
    }

    @Element(name = "ProductCode", required = false)
    private String productCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductcode(String productCode) {
        this.productCode = productCode;
    }
}
