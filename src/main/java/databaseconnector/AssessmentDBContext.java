/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.business.Assessment;

/**
 *
 * @author LENOVO
 */
public class AssessmentDBContext extends DBContext<Assessment>{
    /*FROM 
    assessments a
    LEFT JOIN courses c ON c.subid = a.subid
    LEFT JOIN exams e ON a.aid = e.aid
    LEFT JOIN grades g ON g.eid = e.eid AND g.[sid] = 1
WHERE 
    a.subid = 1 
    AND e.eid IN (
        SELECT MAX(e.eid) AS eid
        FROM assessments a 
        JOIN exams e ON a.aid = e.aid
        GROUP BY a.aid
    )
ORDER BY a.aid;*/
    
    @Override
    public ArrayList<Assessment> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Assessment get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Assessment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Assessment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Assessment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Assessment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
