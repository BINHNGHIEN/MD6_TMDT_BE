package com.example.md6_final_project_be.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    String sex;
    String address;
    LocalDateTime birthday;
    String avatar;
    String password;
    @ManyToOne
    Role role;
    public Admin() {
    }

    public Admin(Long id, String name, String email, String sex, String address, LocalDateTime birthday, String avatar, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.avatar = avatar;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
