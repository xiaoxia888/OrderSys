 package com.chinasofti.ordersys.servlets.login;

 import com.chinasofti.ordersys.vo.UserInfo;
 import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;



















 public class CheckAddUserServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }












   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String userAccount = request.getParameter("name");

     userAccount = new String(userAccount.getBytes("iso8859-1"), "utf-8");

     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

     ArrayList<UserInfo> list = helper.preparedQueryForList(
         "select userAccount from userinfo where userAccount=?",
         new Object[] { userAccount }, UserInfo.class);

     PrintWriter pw = response.getWriter();

     if (list.size() == 0) {

       pw.print("OK");
     }
     else {

       pw.print("FAIL");
     }
   }
 }

