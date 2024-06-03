package br.com.diebold.partsrequest.utils;

public enum DateTimeFormat {

    USADATEANDTIME("EEE MMM dd yyyy hh:mm aaa"), BRAZILDATEANDTIME("dd/MM/yyyy HH:mm:ss"), BRAZILDATEANDTIMER("dd/MM/yyyy HH:mm"), BRAZILDATE("dd/MM/yyyy"), BRAZILDATER("dd/MM/yy"), BRAZILTIME("HH:mm:ss"), BRAZILTIMER("HH:mm"), DATEANDTIME_UTC("yyyy-MM-dd'T'HH:mm:ss");

    private String value;

    private DateTimeFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
