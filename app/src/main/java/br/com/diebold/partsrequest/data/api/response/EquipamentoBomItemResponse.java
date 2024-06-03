package br.com.diebold.partsrequest.data.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EquipamentoBomItemResponse implements Serializable {
    private Integer id;

    @SerializedName("PRODUCT_CODE")
    private String productCode;

    @SerializedName("PRODUCT_NAME")
    private String productName;

    @SerializedName("ITEM_FAMILY")
    private String itemFamily;

    @SerializedName("ITEM_CODE")
    private String itemCode;

    @SerializedName("ITEM_DESCRIPTION")
    private String itemDescription;

    public EquipamentoBomItemResponse() {
    }

    public EquipamentoBomItemResponse(Integer id, String productCode, String productName, String itemFamily, String itemCode, String itemDescription) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.itemFamily = itemFamily;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(String itemFamily) {
        this.itemFamily = itemFamily;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
