/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.rbac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.business.Lecturer;
import model.business.Student;

/**
 *
 * @author LENOVO
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String displayname;
    private ArrayList<Role> roles = new ArrayList<>();
    private Student stu;
    private Lecturer lec;
    private boolean isManager;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        if(lec==null&&isManager==false){
            this.stu = stu;
        }
    }

    public Lecturer getLec() {
        return lec;
    }

    public void setLec(Lecturer lec) {
        if(stu==null&&isManager==false){
            this.lec = lec;
        }
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        if(stu==null&&lec==null){
            this.isManager = isManager;
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
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

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    public Set<Feature> getAllowedFeatures() {
        Set<Feature> features = new HashSet<>();
        for (Role role : roles) {
            features.addAll(role.getFeatures());
        }
        return features;
    }
}