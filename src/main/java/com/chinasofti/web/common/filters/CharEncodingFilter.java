/*    */ package com.chinasofti.web.common.filters;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
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
/*    */ public class CharEncodingFilter
/*    */   implements Filter
/*    */ {
/* 37 */   String encoding = "utf-8";
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
/*    */   public void destroy() {}
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
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/* 61 */     ((HttpServletRequest)request).setCharacterEncoding(this.encoding);
/*    */     
/* 63 */     chain.doFilter(request, response);
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
/*    */   public void init(FilterConfig filterConfig) throws ServletException {
/*    */     try {
/* 78 */       this.encoding = (filterConfig.getInitParameter("encoding") != null) ? filterConfig
/* 79 */         .getInitParameter("encoding") : "utf-8";
/*    */     }
/* 81 */     catch (Exception ex) {
/*    */       
/* 83 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\web\common\filters\CharEncodingFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */