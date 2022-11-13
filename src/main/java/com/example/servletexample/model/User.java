package com.example.servletexample.model;


import com.example.servletexample.runTimeRepository.Users;
import java.io.IOException;

public class User {
    private String Token;
    private String email;
    private int level;
    private int points;
    public User() {
    }

    public User(String email) throws IOException {
        this.email = email;
        this.Token = Users.INSTANCE.getNextId();
        this.level=0;
        this.points=0;
    }

    public void increaseLevelAt(int newLevel){
        this.level=newLevel;
    }

    public void addPoints() {
        this.points += 10;
    }

    public int getPoints() {
        return points;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}