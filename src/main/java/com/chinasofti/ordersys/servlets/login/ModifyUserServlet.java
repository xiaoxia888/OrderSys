    package com.chinasofti.ordersys.servlets.login;

    import com.chinasofti.ordersys.service.admin.UserService;
    import com.chinasofti.ordersys.vo.UserInfo;
    import com.chinasofti.util.web.upload.MultipartRequestParser;
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

































    public class ModifyUserServlet
      extends HttpServlet
    {
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
      }











      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService();

        MultipartRequestParser parser = new MultipartRequestParser();

        UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);

        service.modify(info);


        response.sendRedirect("/OrderSys/logout.order");
      }
    }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\ModifyUserServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */