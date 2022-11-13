package com.example.servletexample.runTimeRepository;

import com.example.servletexample.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public enum Users {
    INSTANCE;

    public boolean ifExists(User user) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

        reader.close();

        for (User u:users){
            if(u.getEmail().equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }

    public void registerUser(User user) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

        reader.close();

        users.add(user);

        try(Writer writer = new FileWriter("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json")) {

            Gson gson = new GsonBuilder().create();
            gson.toJson(users, writer);
        }
    }

    public boolean hasValidCredentials(String email, String token) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

        for (User u:users) {
            if(u.getEmail().equals(email)&& u.getToken().equals(token)){
                return true;
            }
        }

        return false;

    }

    public void addUserPoints(String token) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

        for (User u:users) {
            if(u.getToken().equals(token)){
                u.addPoints();

                int newLevel  = u.getPoints()/100;

                if(newLevel<=4)
                {
                    u.increaseLevelAt(newLevel);
                }

            }
        }

        try(Writer writer = new FileWriter("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json")) {

            Gson gson = new GsonBuilder().create();
            gson.toJson(users, writer);
        }
    }

    public User getUserByToken(String token) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

        for (User u:users) {
            if(u.getToken().equals(token)){
                return u;
            }
        }
        return null;
    }

    public String getNextId() throws IOException {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("D:\\Facultate\\Facultate_Anul3_sem1\\TehnologiWeb\\Servlets-TW\\src\\main\\java\\com\\example\\servletexample\\runTimeRepository\\users.json"));
            List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());

            return String.valueOf(users.size()+1);
        }
            catch (IOException e){
                return String.valueOf(0);
        }
    }

}
