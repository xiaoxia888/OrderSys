package com.chinasofti.ordersys.servlets.kitchen;

import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
    import com.chinasofti.util.web.serverpush.Message;
    import com.chinasofti.util.web.serverpush.MessageHandler;
    import com.chinasofti.util.web.serverpush.ServerPushKey;
    import java.io.PrintWriter;
    import java.util.ArrayList;
    import java.util.Hashtable;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;





public class GetRTDishesServlet extends BaseGetPushMsgServlet {
      public static ArrayList<String> disheses = new ArrayList<>();


      public MessageHandler getHandler(HttpServletRequest request, HttpServletResponse response) {
        //response.setCharacterEncoding("utf-8");
          response.setCharacterEncoding("UTF-8");
          response.setContentType("text/html;charset=utf-8");

        try {
          final PrintWriter out = response.getWriter();

          MessageHandler handler = new MessageHandler()
            {

              public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
              {
                  System.out.println("msg:"+msg);
                  System.out.println("msg1:"+msg.getMsg());
                out.print(msg.getMsg());
              }
            };

            System.out.println(handler.toString());
          return handler;
        }
        catch (Exception ex) {

          ex.printStackTrace();

          return null;
        }
      }








      public void initService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        disheses.add(session.getId());
      }
    }


