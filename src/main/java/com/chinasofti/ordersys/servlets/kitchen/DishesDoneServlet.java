/*    */ package com.chinasofti.ordersys.servlets.kitchen;
/*    */ 
/*    */ import com.chinasofti.util.web.serverpush.MessageProducer;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DishesDoneServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 47 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 61 */     response.setCharacterEncoding("utf-8");

/*    */     
/* 63 */     String tableId = request.getParameter("tableId");
/*    */     
/* 65 */     String dishesName = request.getParameter("dishesName");
    System.out.println("dishesName"+dishesName);
/*    */     
/* 67 */     //dishesName = new String(dishesName.getBytes("iso8859-1"), "utf-8");
/*    */     System.out.println("dishesName2"+dishesName);
/* 69 */     MessageProducer producer = new MessageProducer();
/*    */     
/* 71 */     ArrayList<String> list = GetRTDishesServlet.disheses;
/*    */     
/* 73 */     for (int i = list.size() - 1; i >= 0; i--) {
/*    */       
/* 75 */       String id = list.get(i);
/*    */       
/* 77 */       producer.sendMessage(id, "rtdishes", "桌号[" + tableId + "]的菜品[" + 
/* 78 */           dishesName + "]已经烹制完成，请传菜！");
/*    */       
/* 80 */       list.remove(id);
/*    */     } 
/*    */   }
/*    */ }


