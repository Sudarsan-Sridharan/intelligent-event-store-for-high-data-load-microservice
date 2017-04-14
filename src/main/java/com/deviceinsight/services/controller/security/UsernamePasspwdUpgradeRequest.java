package com.deviceinsight.services.controller.security;

/**
 * Created by ebners on 08/05/16.
 */
public class UsernamePasspwdUpgradeRequest {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private String username;
    private String passwd;

}
