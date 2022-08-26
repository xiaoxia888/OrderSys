/*    */ package com.chinasofti.ordersys.servlets.admin;
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
/*    */ public class SendBordServlet
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
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 62 */     response.setCharacterEncoding("utf-8");
/*    */     
/* 64 */     String bord = request.getParameter("bord");
/*    */     
/* 66 */     MessageProducer producer = new MessageProducer();
/*    */     
/* 68 */     ArrayList<String> list = GetRTBordServlet.bords;
/*    */     
/* 70 */     for (int i = list.size() - 1; i >= 0; i--) {
/*    */       
/* 72 */       String id = list.get(i);
/*    */       
/* 74 */       producer.sendMessage(id, "rtbord", bord);
/*    */       
/* 76 */       list.remove(id);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\SendBordServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */