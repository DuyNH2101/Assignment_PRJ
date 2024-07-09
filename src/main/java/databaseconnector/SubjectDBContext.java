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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.business.Assessment;
import model.business.Subject;

/**
 *
 * @author LENOVO
 */
public class SubjectDBContext extends DBContext<Subject>{
    public Subject getSubjectByID(int wanted_subid) throws ServerException{
        Subject sub = null;
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.subid, s.subname, s.subcodename, s.defaultterm, s.credit,\n"
                    + "	   a.aid, a.acategory, a.[type], a.[weight], a.completioncriteria,\n"
                    + "	   a.duration, a.clo, a.questiontype, a.noquestion,\n"
                    + "	   a.knowledgeandskill, a.gradingguide, a.note\n"
                    + "FROM \n"
                    + "	    subjects s LEFT JOIN assessments a ON s.subid = a.subid\n"
                    + "WHERE \n"    
                    + "		s.subid = ?\n"
                    + "ORDER BY \n"
                    + "		s.subid, a.aid ASC";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, wanted_subid);
            ResultSet rs = stm.executeQuery();
            int c_subid = -1;
            Subject s = null;
            Assessment a = null;
            while(rs.next()){
                int subid = rs.getInt("subid");
                if(subid != 0 && subid != c_subid){
                    c_subid = subid;
                    s = new Subject();
                    s.setId(rs.getInt("subid"));
                    s.setName(rs.getString("subname"));
                    s.setCodename(rs.getString("subcodename"));
                    s.setDefterm(rs.getInt("defaultterm"));
                    s.setCredit(rs.getInt("credit"));
                    sub = s;
                }
                a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setType(rs.getString("type"));
                a.setWeight(rs.getFloat("weight"));
                a.setCompletionCriteria(rs.getInt("completioncriteria"));
                a.setDuration(rs.getString("duration"));
                a.setClo(rs.getString("clo"));
                a.setQuestionType(rs.getString("questiontype"));
                a.setNoQuestion(rs.getString("noquestion"));
                a.setKnowledgeAndSkill(rs.getString("knowledgeandskill"));
                a.setGradingGuide(rs.getString("gradingguide"));
                a.setNote(rs.getString("note"));
                s.getAssessments().add(a);
            }
        } catch (SQLException ex) {
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "sql is bugged " 
                    );
        } finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sub;
    }
    public ArrayList<Subject> getSubjectByName(String name) throws ServerException{
        ArrayList<Subject> subs = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.subid, s.subname, s.subcodename, s.defaultterm, s.credit,\n"
                    + "	   a.aid, a.acategory, a.[type], a.[weight], a.completioncriteria,\n"
                    + "	   a.duration, a.clo, a.questiontype, a.noquestion,\n"
                    + "	   a.knowledgeandskill, a.gradingguide, a.note\n"
                    + "FROM \n"
                    + "	    subjects s LEFT JOIN assessments a ON s.subid = a.subid\n"
                    + "WHERE \n"    
                    + "		s.subname LIKE ?\n"
                    + "ORDER BY \n"
                    + "		s.subid, a.aid ASC";
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+name+"%");
            ResultSet rs = stm.executeQuery();
            int c_subid = -1;
            Subject s = null;
            Assessment a = null;
            while(rs.next()){
                int subid = rs.getInt("subid");
                if(subid != 0 && subid != c_subid){
                    c_subid = subid;
                    s = new Subject();
                    s.setId(rs.getInt("subid"));
                    s.setName(rs.getString("subname"));
                    s.setCodename(rs.getString("subcodename"));
                    s.setDefterm(rs.getInt("defaultterm"));
                    s.setCredit(rs.getInt("credit"));
                    subs.add(s);
                }
                a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setType(rs.getString("type"));
                a.setWeight(rs.getFloat("weight"));
                a.setCompletionCriteria(rs.getInt("completioncriteria"));
                a.setDuration(rs.getString("duration"));
                a.setClo(rs.getString("clo"));
                a.setQuestionType(rs.getString("questiontype"));
                a.setNoQuestion(rs.getString("noquestion"));
                a.setKnowledgeAndSkill(rs.getString("knowledgeandskill"));
                a.setGradingGuide(rs.getString("gradingguide"));
                a.setNote(rs.getString("note"));
                s.getAssessments().add(a);
            }
        } catch (SQLException ex) {
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "sql is bugged " 
                    );
        } finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return subs;
    }
    public ArrayList<Subject> getSubjectByCode(String code) throws ServerException{
        ArrayList<Subject> subs = new ArrayList<>();
        PreparedStatement stm = null;
        try{
            String sql = "SELECT s.subid, s.subname, s.subcodename, s.defaultterm, s.credit,\n"
                    + "	   a.aid, a.acategory, a.[type], a.[weight], a.completioncriteria,\n"
                    + "	   a.duration, a.clo, a.questiontype, a.noquestion,\n"
                    + "	   a.knowledgeandskill, a.gradingguide, a.note\n"
                    + "FROM \n"
                    + "	    subjects s LEFT JOIN assessments a ON s.subid = a.subid\n"
                    + "WHERE \n"    
                    + "		s.subcodename LIKE ?\n"
                    + "ORDER BY \n"
                    + "		s.subid, a.aid ASC";
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+code+"%");
            ResultSet rs = stm.executeQuery();
            int c_subid = -1;
            Subject s = null;
            Assessment a = null;
            while(rs.next()){
                int subid = rs.getInt("subid");
                if(subid != 0 && subid != c_subid){
                    c_subid = subid;
                    s = new Subject();
                    s.setId(rs.getInt("subid"));
                    s.setName(rs.getString("subname"));
                    s.setCodename(rs.getString("subcodename"));
                    s.setDefterm(rs.getInt("defaultterm"));
                    s.setCredit(rs.getInt("credit"));
                    subs.add(s);
                }
                a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setCategory(rs.getString("acategory"));
                a.setType(rs.getString("type"));
                a.setWeight(rs.getFloat("weight"));
                a.setCompletionCriteria(rs.getInt("completioncriteria"));
                a.setDuration(rs.getString("duration"));
                a.setClo(rs.getString("clo"));
                a.setQuestionType(rs.getString("questiontype"));
                a.setNoQuestion(rs.getString("noquestion"));
                a.setKnowledgeAndSkill(rs.getString("knowledgeandskill"));
                a.setGradingGuide(rs.getString("gradingguide"));
                a.setNote(rs.getString("note"));
                s.getAssessments().add(a);
            }
        } catch (SQLException ex) {
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "sql is bugged " 
                    );
        } finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return subs;
    }
    @Override
    public ArrayList<Subject> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
