package com.barisutku.cybersoftproject.models;

public class Enemy extends alive {
    private String type;

    public Enemy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}