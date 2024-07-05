/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.assessment;

import databaseconnector.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.util.ArrayList;
import model.business.Subject;

/**
 *
 * @author LENOVO
 */
public class SubjectChooseController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        request.getRequestDispatcher("../view/curriculum/ChooseSubjectView.jsp").forward(request, response);        
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
        String option = request.getParameter("option");
        String searchtext = request.getParameter("searchtext");
        SubjectDBContext subdb = new SubjectDBContext();
        ArrayList<Subject> subjects = null;
        if(option.equals("name")){
            subjects = subdb.getSubjectByName(searchtext);
        } else{
            subjects = subdb.getSubjectByCode(searchtext);
        }
        if(subjects.isEmpty()){
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "subject is empty " 
                    );
        }
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("../view/curriculum/ChooseSubjectView.jsp").forward(request, response);
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
