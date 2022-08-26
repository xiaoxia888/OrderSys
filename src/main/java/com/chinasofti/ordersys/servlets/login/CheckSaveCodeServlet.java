/*    */ package com.chinasofti.ordersys.servlets.login;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
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
/*    */ 
/*    */ public class CheckSaveCodeServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 48 */     doPost(request, response);
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
/* 62 */     String inputCode = request.getParameter("code");
/*    */     
/* 64 */     HttpSession session = request.getSession();
/*    */     
/* 66 */     String sessionCode = session.getAttribute(
/* 67 */         "web_app_savecode_value").toString();
/*    */ 
/*    */ 
/*    */     
/* 71 */     response.setContentType("text/html");
/*    */     
/* 73 */     PrintWriter out = response.getWriter();
/*    */     
/* 75 */     if (sessionCode.equalsIgnoreCase(inputCode)) {
/*    */       
/* 77 */       out.print("OK");
/*    */     }
/*    */     else {
/*    */       
/* 81 */       out.print("FAIL");
/*    */     } 
/*    */ 
/*    */     
/* 85 */     out.flush();
/*    */     
/* 87 */     out.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\CheckSaveCodeServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */