package br.com.diebold.partsrequest.data.dao.dbHelper;

public enum DataBaseVersion {

    PARTSREQUESTDB(4);

    private int value;

    private DataBaseVersion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}