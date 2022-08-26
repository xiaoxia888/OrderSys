/*    */ package com.chinasofti.ordersys.filters;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginFilter
/*    */   implements Filter
/*    */ {
/*    */   public void destroy() {}
/*    */   
/*    */   public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
/* 61 */     HttpServletRequest request = (HttpServletRequest)arg0;
/*    */     
/* 63 */     HttpServletResponse response = (HttpServletResponse)arg1;
/*    */     
/* 65 */     HttpSession session = request.getSession();
/*    */     
/* 67 */     System.out.println(session.getAttribute("USER_INFO"));
/*    */ 
/*    */     
/* 70 */     if (session.getAttribute("USER_INFO") != null) {
/*    */       
/* 72 */       arg2.doFilter(arg0, arg1);
/*    */     }
/*    */     else {
/*    */       
/* 76 */       response.sendRedirect("/OrderSys");
/*    */     } 
/*    */   }
/*    */   
/*    */   public void init(FilterConfig arg0) throws ServletException {}
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\filters\LoginFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */