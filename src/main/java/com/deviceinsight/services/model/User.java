package com.deviceinsight.services.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue
    private int id;
    private String avatar_url_gravatar_by_email;
    private String reset_password_token;
    private String telephone;
    private int last_token_timestamp = 0;
    private boolean enabled;
    ///@Transient
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private String user_role;
    /*	@Length(min = 4, max=16)

        @NotNull(message="")
        @NotEmpty(message="")
        @Pattern(regexp="^[a-z0-9_-]*$", message="enthält unzulässige Zeichen")*/
    private String username;
    private String first_name;
    private String last_name;
    /*@Length(min = 8, max=255)
    @NotNull(message="")
    @NotEmpty(message="")*/
    private String password;

    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url_gravatar_by_email() {
        return avatar_url_gravatar_by_email;
    }

    public void setAvatar_url_gravatar_by_email(String avatar_url_gravatar_by_email) {
        this.avatar_url_gravatar_by_email = avatar_url_gravatar_by_email;
    }

    public String getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(String reset_password_token) {
        this.reset_password_token = reset_password_token;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLast_token_timestamp() {
        return last_token_timestamp;
    }

    public void setLast_token_timestamp(int last_token_timestamp) {
        this.last_token_timestamp = last_token_timestamp;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserRole() {
        return user_role;
    }

    public void setUserRole(String user_role) {
        this.user_role = user_role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
