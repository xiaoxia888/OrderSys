 package com.chinasofti.ordersys.servlets.admin;

 import com.chinasofti.ordersys.service.waiters.OrderService;
 import java.io.IOException;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

































 public class ChangeOrderStateServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }












   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     Integer orderId = new Integer(request.getParameter("orderId"));

     int state = Integer.parseInt(request.getParameter("state"));



     OrderService service = new OrderService();

     service.changeState(orderId, state);
   }
 }

