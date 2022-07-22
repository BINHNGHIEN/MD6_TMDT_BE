package com.example.md6_final_project_be.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DetailUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sex;
    private String address;
    private LocalDateTime birthday;
    @OneToOne
    private User user;
    public DetailUser() {
    }

    public DetailUser(Long id, String sex, String address, LocalDateTime birthday, User user) {
        this.id = id;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
