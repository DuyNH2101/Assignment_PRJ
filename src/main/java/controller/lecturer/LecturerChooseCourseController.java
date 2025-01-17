/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import databaseconnector.CourseDBContext;
import databaseconnector.ExamDBContext;
import databaseconnector.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.business.Course;
import model.business.Exam;
import model.business.Lecturer;
import model.business.Subject;
import model.rbac.User;

/**
 *
 * @author LENOVO
 */
public class LecturerChooseCourseController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LecturerChooseCourseController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LecturerChooseCourseController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CourseDBContext cdb = new CourseDBContext();
        Lecturer l = ((User)request.getSession().getAttribute("user")).getLec();
        if(l==null){
            if(((User)request.getSession().getAttribute("user")).getManager()!=null){
                request.getRequestDispatcher("../view/exam/ManagerChooseCourse.jsp").forward(request, response);
                return;
            }
        }
        ArrayList<Course> courses = cdb.getCoursesByLecturerID(l.getId());  
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../../view/exam/LecturerChooseCourse.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //int cid = Integer.parseInt(request.getParameter("cname"));
        if(request.getSession().getAttribute("choose_course_action")==null){
            String action = request.getParameter("choose_course_action");
            if(action.equals("create")){
                int subid = Integer.parseInt(request.getParameter("subjectId"));
                SubjectDBContext sdb = new SubjectDBContext();
                Subject subject = sdb.getSubjectByID(subid);
                request.setAttribute("subject", subject);
                request.getRequestDispatcher("../../view/exam/LecturerCreateExam.jsp").forward(request, response);
            } else if(action.equals("take")){
                int cid = Integer.parseInt(request.getParameter("cid"));


                ExamDBContext db = new ExamDBContext();
                ArrayList<Exam> exams = db.getExamsByCourse(cid);
                request.setAttribute("exams", exams);

                request.getRequestDispatcher("../view/exam/LecturerChooseExamToTake.jsp").forward(request, response);
            }
        } else {
            if(request.getSession().getAttribute("choose_course_action").equals("create")){
                int subid = Integer.parseInt(request.getParameter("subjectId"));
                SubjectDBContext sdb = new SubjectDBContext();
                Subject subject = sdb.getSubjectByID(subid);
                request.setAttribute("subject", subject);
                request.getRequestDispatcher("../../view/exam/LecturerCreateExam.jsp").forward(request, response);
            } else if(request.getSession().getAttribute("choose_course_action").equals("take")){
                int cid = Integer.parseInt(request.getParameter("cid"));


                ExamDBContext db = new ExamDBContext();
                ArrayList<Exam> exams = db.getExamsByCourse(cid);
                request.setAttribute("exams", exams);

                request.getRequestDispatcher("../view/exam/LecturerChooseExamToTake.jsp").forward(request, response);
            }
        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
