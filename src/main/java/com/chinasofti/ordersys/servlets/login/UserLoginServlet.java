 package com.chinasofti.ordersys.servlets.login;

 import com.chinasofti.ordersys.service.DomainProtectedService;
 import com.chinasofti.ordersys.service.login.LoginService;
 import com.chinasofti.ordersys.vo.UserInfo;
 import com.chinasofti.util.sec.Passport;
 import com.chinasofti.util.web.upload.MultipartRequestParser;
 import java.io.IOException;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;




 public class UserLoginServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }






   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     MultipartRequestParser parser = new MultipartRequestParser();

     UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);

     Passport passport = new Passport();

     info.setUserPass(passport.md5(info.getUserPass()));

     DomainProtectedService domainService = new DomainProtectedService();

     if (domainService.isFromSameDomain()) {

       LoginService loginService = new LoginService();

    //System.out.println(loginService.login(info));
       switch (loginService.login(info)) {



         case 0:
           request.setAttribute("ERROR_MSG", "用户名不存在！");

           request.setAttribute("USER_INFO", info);

           request.getRequestDispatcher("/pages/login.jsp").forward(
               (ServletRequest)request, (ServletResponse)response);
           break;


         case 1:
           request.setAttribute("ERROR_MSG", "用户密码不匹配！");

           request.setAttribute("USER_INFO", info);

           request.getRequestDispatcher("/pages/login.jsp").forward(
               (ServletRequest)request, (ServletResponse)response);
           break;


         case 5:
           request.getSession().setAttribute("USER_INFO",
               loginService.getLoginUser());
       //System.out.println(loginService.getLoginUser());

           switch (loginService.getLoginUser().getRoleId()) {


             case 1:
               response.sendRedirect("/OrderSys/toadminmain.order");
               break;


             case 2:
               response.sendRedirect("/OrderSys/tokitchenmain.order");
               break;


             case 3:
               response.sendRedirect("/OrderSys/towaitermain.order");
               break;
           }


           break;


         case 3:
           request.setAttribute("ERROR_MSG", "该用户已经被锁定！");

           request.setAttribute("USER_INFO", loginService.getLoginUser());

           request.getRequestDispatcher("/pages/login.jsp").forward(
               (ServletRequest)request, (ServletResponse)response);
           break;


         case 2:
           request.setAttribute("ERROR_MSG", "该用户已经在线，不能重复登录！");

           request.setAttribute("USER_INFO", info);

           request.getRequestDispatcher("/pages/login.jsp").forward(
               (ServletRequest)request, (ServletResponse)response);
           break;
       }


     } else {
       request.getSession().setAttribute("USER_INFO", info);

       response.sendRedirect("todomainerror.order");
     }
   }
 }

