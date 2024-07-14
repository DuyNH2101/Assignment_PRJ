/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Assessment;
import model.business.Exam;
import model.business.Grade;
import model.business.Student;

/**
 *
 * @author LENOVO
 */
public class GradeDBContext extends DBContext<Grade>{
    public ArrayList<Grade> getAverageGradeOfStudentsBySemIdAndSubId(int subid, int semid){
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.[sid], s.sname,\n"
                    + "	   ave.score\n"
                    + "FROM students s JOIN (SELECT SUM(g.score * a.[weight] / 100) AS score, g.[sid]\n"
                    + "    FROM exams e\n"
                    + "    INNER JOIN grades g ON g.eid = e.eid\n"
                    + "    INNER JOIN assessments a ON e.aid = a.aid\n"
                    + "	INNER JOIN subjects sub ON a.subid = sub.subid\n"
                    + "	INNER JOIN courses c ON c.subid = sub.subid\n"
                    + "	INNER JOIN semesters sem ON sem.semid = c.semid\n"
                    + "    WHERE sub.subid = ?\n"
                    + "	  AND g.score != -1\n"
                    + "	  AND sem.semid = ?\n"
                    + "      AND e.eid IN (\n"
                    + "        SELECT MAX(e.eid) AS eid\n"
                    + "        FROM assessments a \n"
                    + "        JOIN exams e ON a.aid = e.aid\n"
                    + "        GROUP BY a.aid\n"
                    + "      )\n"
                    + "    GROUP BY a.subid, g.[sid]) ave ON ave.[sid] = s.[sid]";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            stm.setInt(2, semid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));
                
                Student s = new Student();
                s.setId(rs.getByte("sid"));
                s.setName(rs.getString("sname"));
                
                g.setStudent(s);
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }
    public ArrayList<Grade> getAverageGradeOfStudentsByCourseId(int cid){
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.[sid], s.sname,\n"
                    + "	   ave.score\n"
                    + "FROM students s JOIN (SELECT SUM(g.score * a.[weight] / 100) AS score, g.[sid]\n"
                    + "    FROM exams e\n"
                    + "    INNER JOIN grades g ON g.eid = e.eid\n"
                    + "    INNER JOIN assessments a ON e.aid = a.aid\n"
                    + "	INNER JOIN courses c ON a.subid = c.subid\n"
                    + "    WHERE c.cid = ?\n"
                    + "	  AND g.score != -1\n"
                    + "      AND e.eid IN (\n"
                    + "        SELECT MAX(e.eid) AS eid\n"
                    + "        FROM assessments a \n"
                    + "        JOIN exams e ON a.aid = e.aid\n"
                    + "        GROUP BY a.aid\n"
                    + "      )\n"
                    + "    GROUP BY a.subid, g.[sid]) ave ON ave.[sid] = s.[sid]";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));
                
                Student s = new Student();
                s.setId(rs.getByte("sid"));
                s.setName(rs.getString("sname"));
                
                g.setStudent(s);
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }
    public ArrayList<Grade> getGradeForCourseOfStudent(int sid, int cid){
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT \n"
                    + "	a.aid, a.acategory, a.[type], a.[weight], a.completioncriteria, \n"
                    + "	e.eid,\n"
                    + "	COALESCE(g.score, -1.0) AS score\n"
                    + "FROM \n"
                    + "    assessments a\n"
                    + "    LEFT JOIN courses c ON c.subid = a.subid\n"
                    + "    LEFT JOIN exams e ON a.aid = e.aid\n"
                    + "    LEFT JOIN grades g ON g.eid = e.eid AND g.[sid] = ?\n"
                    + "WHERE \n"
                    + "    c.cid = ?\n"
                    + "    AND e.eid IN (\n"
                    + "        SELECT MAX(e.eid) AS eid\n"
                    + "        FROM assessments a \n"
                    + "        JOIN exams e ON a.aid = e.aid\n"
                    + "        GROUP BY a.aid\n"
                    + "    )\n"
                    + "ORDER BY a.aid;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setType(rs.getString("type"));
                a.setWeight(rs.getFloat("weight"));
                a.setCompletionCriteria(rs.getInt("completioncriteria"));
                e.setAssessment(a);
                g.setExam(e);
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }
    public ArrayList<Grade> getGradesFromExamIds(ArrayList<Integer> eids) {
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT eid,sid,score FROM grades WHERE (1>2)";
            for (Integer eid : eids) {
                sql += " OR eid = ?";
            }

            stm = connection.prepareStatement(sql);

            for (int i = 0; i < eids.size(); i++) {
                stm.setInt((i + 1), eids.get(i));
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));

                Student s = new Student();
                s.setId(rs.getInt("sid"));
                g.setStudent(s);

                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                g.setExam(e);

                grades.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }

    public void insertGradesForCourse(int cid, ArrayList<Grade> grades) {
        String sql_remove = "DELETE grades WHERE sid = ? AND eid = ?";
        String sql_insert = "INSERT INTO [grades]\n"
                + "           ([eid]\n"
                + "           ,[sid]\n"
                + "           ,[score])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        
        PreparedStatement stm_remove =null;
        ArrayList<PreparedStatement> stm_inserts = new ArrayList<>();
        
        try {
            connection.setAutoCommit(false);
            
            
            for (Grade grade : grades) {
                stm_remove = connection.prepareStatement(sql_remove);
                stm_remove.setInt(1, grade.getStudent().getId());
                stm_remove.setInt(2, grade.getExam().getId());
                stm_remove.executeUpdate();
                PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
                stm_insert.setInt(1, grade.getExam().getId());
                stm_insert.setInt(2,grade.getStudent().getId());
                stm_insert.setFloat(3, grade.getScore());
                stm_insert.executeUpdate();
                stm_inserts.add(stm_insert);
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
                if(stm_remove!=null){
                    stm_remove.close();
                }
                for (PreparedStatement stm_insert : stm_inserts) {
                    stm_insert.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    @Override
    public ArrayList<Grade> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
