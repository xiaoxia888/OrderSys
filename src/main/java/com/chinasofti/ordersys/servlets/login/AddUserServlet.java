 package com.chinasofti.ordersys.servlets.login;

 import com.chinasofti.ordersys.service.admin.UserService;
 import com.chinasofti.ordersys.vo.UserInfo;
 import com.chinasofti.util.web.upload.MultipartRequestParser;
 import com.chinasofti.web.common.taglib.TokenTag;
 import java.io.IOException;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;











 public class AddUserServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }











   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //if (TokenTag.isTokenValid()) {

       MultipartRequestParser parser = new MultipartRequestParser();

       UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);

       UserService service = new UserService();

       service.addUser(info);

       TokenTag.releaseToken();
     //}

     response.sendRedirect("/OrderSys/touseradmin.order");
   }

   public void init() throws ServletException {}
 }


