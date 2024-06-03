package br.com.diebold.partsrequest.data.dao.model;

import br.com.diebold.partsrequest.modelView.EquipamentoBomItemView;

public class EquipamentoBomModel implements IModel {
    private Integer id;
    private Integer idEquipamentoBom;
    private String productCode;
    private String productName;
    private String itemFamily;
    private String itemCode;
    private String itemDescription;

    public EquipamentoBomModel() {
    }

    public EquipamentoBomModel(Integer id, Integer idEquipamentoBom, String productCode, String productName, String itemFamily, String itemCode, String itemDescription) {
        this.id = id;
        this.idEquipamentoBom = idEquipamentoBom;
        this.productCode = productCode;
        this.productName = productName;
        this.itemFamily = itemFamily;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
    }

    public EquipamentoBomModel(EquipamentoBomItemView equipamentoBomItemView) {
        if(equipamentoBomItemView != null) {
            this.id = equipamentoBomItemView.getId();
            this.idEquipamentoBom = equipamentoBomItemView.getIdEquipamentoBom();
            this.productCode = equipamentoBomItemView.getProductCode();
            this.productName = equipamentoBomItemView.getProductName();
            this.itemFamily = equipamentoBomItemView.getItemFamily();
            this.itemCode = equipamentoBomItemView.getItemCode();
            this.itemDescription = equipamentoBomItemView.getItemDescription();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEquipamentoBom() {
        return idEquipamentoBom;
    }

    public void setIdEquipamentoBom(Integer idEquipamentoBom) {
        this.idEquipamentoBom = idEquipamentoBom;
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
