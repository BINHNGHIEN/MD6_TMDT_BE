package com.example.md6_final_project_be.model;

import javax.persistence.*;

@Entity
@Table(name = "categorys", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameCategory"
        })
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;
    private String avatarCategory;
    @ManyToOne
    AppUser appUser;

    public Category() {
    }

    public Category(Long id, String nameCategory, String avatarCategory, AppUser appUser) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.avatarCategory = avatarCategory;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getAvatarCategory() {
        return avatarCategory;
    }

    public void setAvatarCategory(String avatarCategory) {
        this.avatarCategory = avatarCategory;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
