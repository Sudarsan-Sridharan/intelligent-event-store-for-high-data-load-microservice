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
    private Long average_time_service;
    private Long average_time_pay;
    private int service_counter;
    private int pay_counter;
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
    private String password_autologin;
    private String email;

    public String getAvatar_url_gravatar_by_email() {
        return avatar_url_gravatar_by_email;
    }

    public void setAvatar_url_gravatar_by_email(String avatar_url_gravatar_by_email) {
        this.avatar_url_gravatar_by_email = avatar_url_gravatar_by_email;
    }

    public Long getAverage_time_service() {
        return average_time_service;
    }

    public void setAverage_time_service(Long average_time_service) {
        this.average_time_service = average_time_service;
    }

    public Long getAverage_time_pay() {
        return average_time_pay;
    }

    public void setAverage_time_pay(Long average_time_pay) {
        this.average_time_pay = average_time_pay;
    }

    public int getService_counter() {
        return service_counter;
    }

    public void setService_counter(int service_counter) {
        this.service_counter = service_counter;
    }

    public int getPay_counter() {
        return pay_counter;
    }

    public void setPay_counter(int pay_counter) {
        this.pay_counter = pay_counter;
    }

    public String getPassword_autologin() {
        return password_autologin;
    }

    public void setPassword_autologin(String password_autologin) {
        this.password_autologin = password_autologin;
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

    public String getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(String reset_password_token) {
        this.reset_password_token = reset_password_token;
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

    // @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getUserRole() {
        return this.user_role;
    }

    public void setUserRole(String userRole) {
        this.user_role = userRole;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
