package br.com.diebold.partsrequest.modelView;

import br.com.diebold.partsrequest.data.api.response.EquipamentoBomItemResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoItemResponse;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoBomModel;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoModel;

public class EquipamentoBomItemView {
    private Integer id;
    private Integer idEquipamentoBom;
    private String productCode;
    private String productName;
    private String itemFamily;
    private String itemCode;
    private String itemDescription;

    public EquipamentoBomItemView() {
    }

    public EquipamentoBomItemView(EquipamentoBomItemResponse equipamentoBomItemResponse) {
        if(equipamentoBomItemResponse != null) {

            this.idEquipamentoBom = equipamentoBomItemResponse.getId();
            this.productCode = equipamentoBomItemResponse.getProductCode();
            this.productName = equipamentoBomItemResponse.getProductName();
            this.itemFamily = equipamentoBomItemResponse.getItemFamily();
            this.itemCode = equipamentoBomItemResponse.getItemCode();
            this.itemDescription = equipamentoBomItemResponse.getItemDescription();
        }
    }

    public EquipamentoBomItemView(EquipamentoBomModel equipamentoBomModel) {
        if(equipamentoBomModel != null) {
            this.id = equipamentoBomModel.getId();
            this.idEquipamentoBom = equipamentoBomModel.getIdEquipamentoBom();
            this.productCode = equipamentoBomModel.getProductCode();
            this.productName = equipamentoBomModel.getProductName();
            this.itemFamily = equipamentoBomModel.getItemFamily();
            this.itemCode = equipamentoBomModel.getItemCode();
            this.itemDescription = equipamentoBomModel.getItemDescription();
        }
    }

    public EquipamentoBomItemView(EquipamentoBomItemView equipamentoBomItemView) {
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
