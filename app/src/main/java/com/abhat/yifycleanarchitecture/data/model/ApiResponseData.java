package com.abhat.yifycleanarchitecture.data.model;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class ApiResponseData {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private String status_message;
    private Data data;
}
