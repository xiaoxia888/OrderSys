package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteUserServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer userId = new Integer(request.getParameter("userId"));

    UserService service = new UserService();

    service.deleteUser(userId);
  }
}

