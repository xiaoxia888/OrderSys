package com.chinasofti.ordersys.servlets.login;

import com.chinasofti.ordersys.service.login.CheckUserPassService;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.web.upload.MultipartRequestParser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

































public class CheckUserPassServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    MultipartRequestParser parser = new MultipartRequestParser();

    UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);

    CheckUserPassService service = new CheckUserPassService();

    PrintWriter pw = response.getWriter();

    if (service.checkPass(info)) {

      pw.print("OK");
    }
    else {

      pw.print("FAIL");
    }
  }
}

