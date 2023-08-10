package com.example.demopdp.controller.model;

import jakarta.persistence.Entity;

public class User {
    private long id;

    private String common_name;
    private String legal_name;

    public User() {

    }
    public User(long id, String common_name, String legal_name) {
        this.id = id;
        this.common_name = common_name;
        this.legal_name = legal_name;
    }
    public User(String common_name, String legal_name) {
        this.common_name = common_name;
        this.legal_name = legal_name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String name) {
        this.common_name = name;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String email) {
        this.legal_name = email;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + common_name + ", email=" + legal_name + "]";
    }

}
