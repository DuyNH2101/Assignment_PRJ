/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import databaseconnector.UserDBContext;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.security.DrbgParameters;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import model.rbac.Feature;
import model.rbac.Role;
import model.rbac.User;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.base.BaseXMLFilter;

/**
 *
 * @author LENOVO
 */
public class URLRequiredAuthenticationFilter extends BaseXMLFilter {

    private List<String> urls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // Initialize acceptedUrls list
            urls = new ArrayList<>();
            // Extract URL elements
            NodeList urlNodes = readUrlsFromXML(filterConfig, "required_auth_urls");
            for (int i = 0; i < urlNodes.getLength(); i++) {
                urls.add(urlNodes.item(i).getTextContent());
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
        User user = (User) httpRequest.getSession().getAttribute("user");
        boolean isRequired = false;
        // Example: Log the accepted URLs
        for (String url : urls) {
            if (url.equals(current)) {
                isRequired = true;
                break;
            }
        }
        if (isRequired && user == null) {
            String message = "you are not yet authenticated! List of required authentication:" + urls.toString();
            throw new ServerException(message);
        } else if (isRequired && user != null) {
            boolean isPassed = false;
            
            for (Feature feature : user.getRole().getFeatures()) {
                if (feature.getUrl().equals(current)) {
                    isPassed = true;
                    break;
                }
            }
            
            if (isPassed) {
                chain.doFilter(request, response);
            } else {
                throw new ServerException(HttpServletResponse.SC_FORBIDDEN + ":"
                        + "Access Denied"
                );
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }

}
