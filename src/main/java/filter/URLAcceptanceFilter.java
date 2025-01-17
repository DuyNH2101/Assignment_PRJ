/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.InputStream;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.rbac.User;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.base.BaseXMLFilter;


/**
 *
 * @author sonng
 */
public class URLAcceptanceFilter extends BaseXMLFilter {
    
    private List<String> acceptedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // Initialize acceptedUrls list
            acceptedUrls = new ArrayList<>();
            // Extract URL elements
            NodeList urlNodes = readUrlsFromXML(filterConfig, "accepted-urls");
            for (int i = 0; i < urlNodes.getLength(); i++) {
                acceptedUrls.add(urlNodes.item(i).getTextContent());
            }
        } catch (ServletException | IOException | ParserConfigurationException | DOMException | SAXException e) {
            throw new ServletException("Failed to parse config file", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String current = httpRequest.getServletPath();
        HttpSession session = httpRequest.getSession();
        if((!current.equals("/lecturer/exam/choose"))||(!current.equals("/lecturer/exam/take"))||(!current.equals("/view/exam/LecturerCreateExam.jsp"))||(!current.equals("/view/exam/LecturerChooseCourse.jsp"))){
            if(httpRequest.getSession().getAttribute("choose_course_action")!=null){
                httpRequest.getSession().removeAttribute("choose_course_action");
            }
        }
        User user = (User)session.getAttribute("user");
        if(user==null){
            if(current.equals("/login")||current.equals("/logout")||current.equals("/view/auth/login.jsp")){
                chain.doFilter(request, response);
                return;
            }else{
                request.setAttribute("error", "You haven't logged in yet!");
                request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
                return;
            }
        }
        
        boolean isAllowed = false;
        // Example: Log the accepted URLs
        for (String url : acceptedUrls) {
            if(url.equals(current))
            {
                isAllowed = true;
                break;
            }
        }
        //chain.doFilter(request, response);
        if(isAllowed)
        // Continue with the filter chain
            chain.doFilter(request, response);
        else
            throw new ServerException(HttpServletResponse.SC_FORBIDDEN+":"+
                    "Access Denied " + current
                    );
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }
    
}