/*    */ package com.chinasofti.ordersys.servlets.common;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class InitSysServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
/*    */   
/*    */   public void init() throws ServletException {
/* 64 */     String sysName = "中软国际-餐厅到店点餐系统";
/*    */ 
/*    */     
/*    */     try {
/* 68 */       sysName = (getServletConfig().getInitParameter("sysname") != null) ? getServletConfig()
/* 69 */         .getInitParameter("sysname") : "中软国际-餐厅到店点餐系统";
/*    */     }
/* 71 */     catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */     
/* 75 */     getServletContext().setAttribute("ORDER_SYS_NAME", sysName);
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\common\InitSysServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */