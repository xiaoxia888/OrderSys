package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.util.web.serverpush.MessageProducer;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















public class SendBordServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }












  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("utf-8");

    String bord = request.getParameter("bord");

    MessageProducer producer = new MessageProducer();

    ArrayList<String> list = GetRTBordServlet.bords;

    for (int i = list.size() - 1; i >= 0; i--) {

      String id = list.get(i);

      producer.sendMessage(id, "rtbord", bord);

      list.remove(id);
    }
  }
}


