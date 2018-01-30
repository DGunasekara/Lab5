/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuuri
 */

@WebServlet(
       // name = "Address Book",
        urlPatterns = {"/search","/add"},
        initParams = {@WebInitParam(name="fileName",value="/addressBook.txt")}
)
public class AddressBookServlet extends HttpServlet {
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String str = request.getParameter("name");
        HttpSession session = request.getSession();
        
        String id = session.getId();
        
        AddressBook addressbook;
     
       if(session.isNew()){
           
           addressbook = new AddressBook();
           session.setAttribute(id,addressbook );
            
      }else{         
         addressbook = (AddressBook)session.getAttribute(id);
       }
       
       PrintWriter out = response.getWriter(); 
       out.println(addressbook.search(str));
   }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AddressBook addressbook;
//        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String details = request.getParameter("details");
        HttpSession session = request.getSession();
        String id = session.getId();
       if(session.isNew()){
           
           addressbook = new AddressBook();
           session.setAttribute(id,addressbook );

      }else{
           addressbook = (AddressBook)session.getAttribute(id);
         
    }
           PrintWriter out = response.getWriter();   
          
           addressbook.add(name,details);
           response.setStatus(SC_OK);           
           out.println("Entries for "+request.getParameter("name") +" are added.");        
}
    
    @Override
    public String getServletInfo() {
        return "Short description";
    } 
}
