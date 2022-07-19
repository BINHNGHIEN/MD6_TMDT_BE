package com.example.md6_final_project_be.model;

public enum RoleName {
    ADMIN(2), PM(3), USER(1);

    private int value;

    private RoleName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
