package br.com.diebold.partsrequest.data.dao.dbHelper;

public enum DataBaseName {

    PARTSREQUESTDB("PartsRequestdb.sqlite");

    private String value;

    private DataBaseName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}