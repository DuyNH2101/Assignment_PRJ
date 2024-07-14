/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import jakarta.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Assessment;
import model.business.Exam;
import model.business.Subject;

/**
 *
 * @author LENOVO
 */
public class ExamDBContext extends DBContext<Exam>{
    
    public void lecturerCreateExam(int aid, String from, int duration){
        PreparedStatement stm = null;
        try{
            connection.setAutoCommit(false);
            String sql = "INSERT INTO exams([aid], [from], [duration]) VALUES\n"
                    + "( ?, ? ,?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            LocalDateTime ldt = LocalDateTime.parse(from);           
            stm.setTimestamp(2, Timestamp.valueOf(ldt));
            stm.setInt(3, duration);
            stm.executeUpdate();
            connection.commit();
        }  catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally{
            try {
                connection.setAutoCommit(true);
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public ArrayList<Exam> getExamsByCourse(int cid){
        ArrayList<Exam> exams = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid,e.duration,e.[from],\n"
                    + "	   a.aid,a.acategory,a.weight,\n"
                    + "	   sub.subid,sub.subname\n"
                    + "FROM exams e INNER JOIN assessments a ON a.aid = e.aid\n"
                    + "	         INNER JOIN subjects sub ON sub.subid = a.subid\n"
                    + "			 INNER JOIN courses c ON c.subid = sub.subid\n"
                    + "WHERE c.cid = ?"
                    + "    AND e.eid IN (\n"
                    + "        SELECT MAX(e.eid) AS eid\n"
                    + "        FROM assessments a \n"
                    + "        JOIN exams e ON a.aid = e.aid\n"
                    + "        GROUP BY a.aid\n"
                    + "    )\n";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                e.setDuration(rs.getInt("duration"));
                e.setFrom(rs.getTimestamp("from"));

                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setWeight(rs.getFloat("weight"));

                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                a.setSubject(sub);

                e.setAssessment(a);
                exams.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
    }
    public ArrayList<Exam> getExamsByExamIds(ArrayList<Integer> eids) {
        ArrayList<Exam> exams = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid,e.[from],a.aid,a.acategory,a.weight FROM exams e INNER JOIN assessments a ON a.aid = e.aid\n"
                    + "						WHERE (1>2)";
            
            for (Integer eid : eids) {
                sql+=" OR eid = ?";
            }
            
            stm = connection.prepareStatement(sql);
            
            for (int i = 0; i < eids.size(); i++) {
                stm.setInt((i+1), eids.get(i));
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                e.setFrom(rs.getTimestamp("from"));

                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setWeight(rs.getFloat("weight"));

                e.setAssessment(a);
                exams.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
    }
    @Override
    public ArrayList<Exam> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exam get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
