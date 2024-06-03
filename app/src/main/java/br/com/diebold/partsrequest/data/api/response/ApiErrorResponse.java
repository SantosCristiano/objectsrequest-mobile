package br.com.diebold.partsrequest.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiErrorResponse implements Serializable {


    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("status")
    @Expose
    private Integer status;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("path")
    @Expose
    private String path;


    public ApiErrorResponse(){

    }

    public void setError(ApiErrorResponse aer){
        this.timestamp = aer.timestamp;
        this.status = aer.status;
        this.message = aer.message;
        this.path = aer.path;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


//
//    "timestamp": "2021-08-06T17:51:21.761+0000",
//            "status": 403,
//            "error": "Forbidden",
//            "message": "Access Denied",
//            "path": "/pedidos/todos"
}
