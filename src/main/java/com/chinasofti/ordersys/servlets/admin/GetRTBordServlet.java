package com.chinasofti.ordersys.servlets.admin;

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












public class GetRTBordServlet
  extends BaseGetPushMsgServlet
{
  public static ArrayList<String> bords = new ArrayList<>();













  public MessageHandler getHandler(HttpServletRequest request, HttpServletResponse response) {
    response.setCharacterEncoding("utf-8");



    try {
      final PrintWriter out = response.getWriter();

      MessageHandler handler = new MessageHandler()
        {



          public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
          {
            out.print(msg.getMsg());
          }
        };


      return handler;
    }
    catch (Exception ex) {

      ex.printStackTrace();

      return null;
    }
  }















  public void initService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    bords.add(session.getId());
  }
}

