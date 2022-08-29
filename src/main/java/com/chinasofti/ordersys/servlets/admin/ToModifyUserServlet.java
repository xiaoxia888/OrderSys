package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.UserInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;































public class ToModifyUserServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    UserService service = new UserService();

    UserInfo info = service.getUserById(new Integer(request
          .getParameter("userId")));

    request.setAttribute("MODIFY_INFO", info);

    request.getRequestDispatcher("/pages/admin/modifyuser.jsp").forward(
        (ServletRequest)request, (ServletResponse)response);
  }
}


