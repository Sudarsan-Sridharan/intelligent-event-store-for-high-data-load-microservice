package com.deviceinsight.services.ecommerce.api;

/**
 * Created by ebners on 21.05.17.
 */
public class ESExampleObject {

    private String user;
    private String usera;
    private String postDate;
    private String message;

    public ESExampleObject() {
    }

    public ESExampleObject(String user, String usera, String postDate, String message) {
        this.user = user;
        this.usera = usera;
        this.postDate = postDate;
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsera() {
        return usera;
    }

    public void setUsera(String usera) {
        this.usera = usera;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
