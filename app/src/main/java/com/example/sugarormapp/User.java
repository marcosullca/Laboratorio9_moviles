package com.example.sugarormapp;

import com.orm.dsl.Table;

@Table
public class User {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String path;
    public User() {
    }

    public User(String fullname, String email, String password,String path) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.path=path;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

}
