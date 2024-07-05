/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.business;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Subject {
    private int id;
    private String name;
    private String codename;
    private int defterm;
    private int credit;
    final private ArrayList<Assessment> assessments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public int getDefterm() {
        return defterm;
    }

    public void setDefterm(int defterm) {
        this.defterm = defterm;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

//    private void setAssessments(ArrayList<Assessment> assessments) {
//        this.assessments = assessments;
//    }

//    
    
}
