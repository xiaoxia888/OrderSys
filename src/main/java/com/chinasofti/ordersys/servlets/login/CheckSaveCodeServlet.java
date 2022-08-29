package com.chinasofti.ordersys.servlets.login;

 import java.io.IOException;
 import java.io.PrintWriter;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;


































 public class CheckSaveCodeServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }











   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String inputCode = request.getParameter("code");

     HttpSession session = request.getSession();

     String sessionCode = session.getAttribute(
         "web_app_savecode_value").toString();



     response.setContentType("text/html");

     PrintWriter out = response.getWriter();

     if (sessionCode.equalsIgnoreCase(inputCode)) {

       out.print("OK");
     }
     else {

       out.print("FAIL");
     }


     out.flush();

     out.close();
   }
 }

