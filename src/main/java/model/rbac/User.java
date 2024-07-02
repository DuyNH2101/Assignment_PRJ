/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.rbac;


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
    private Role role ;
    private Student stu;
    private Lecturer lec;
    private Manager manager;

    public Student getStu() {
        return stu;
    }

    public void setStudent(Student stu) {
        if(lec==null&&manager==null){
            this.stu = stu;
        }
    }

    public Lecturer getLec() {
        return lec;
    }

    public void setLec(Lecturer lec) {
        if(stu==null&&manager==null){
            this.lec = lec;
        }
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        if(stu==null&&lec==null){
            this.manager = manager;
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
    
}