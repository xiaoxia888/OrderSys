/*    */ package com.chinasofti.ordersys.filters;
/*    */ 
/*    */ import com.chinasofti.ordersys.vo.UserInfo;
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
/*    */ 
/*    */ public class AdminFilter
/*    */   implements Filter
/*    */ {
/*    */   public void destroy() {}
/*    */
/*    */   public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
/* 63 */     HttpServletRequest request = (HttpServletRequest)arg0;
/*    */
/* 65 */     HttpServletResponse response = (HttpServletResponse)arg1;
/*    */
/* 67 */     HttpSession session = request.getSession();
/*    */
/*    */
    System.out.println(session.getAttribute("USER_INFO"));
/* 70 */     if (session.getAttribute("USER_INFO") != null && (
/* 71 */       (UserInfo)session.getAttribute("USER_INFO")).getRoleId() == 1) {
/*    */
/* 73 */       arg2.doFilter(arg0, arg1);
/*    */     }
/*    */     else {
/*    */
/* 77 */       response.sendRedirect("/OrderSys");
/*    */     }
/*    */   }
/*    */
/*    */   public void init(FilterConfig arg0) throws ServletException {}
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\filters\AdminFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */