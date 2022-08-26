/*     */ package com.chinasofti.web.common.httpequest;
/*     */ 
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpRequestContext
/*     */ {
/*     */   private HttpServletRequest request;
/*     */   private HttpServletResponse response;
/*     */   private ServletContext servletContext;
/*     */   
/*     */   private HttpRequestContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
/*  55 */     this.request = request;
/*     */     
/*  57 */     this.response = response;
/*     */     
/*  59 */     this.servletContext = servletContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static ThreadLocal<HttpRequestContext> currentContext = new ThreadLocal<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setHttpRequestContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
/*  80 */     HttpRequestContext context = new HttpRequestContext(request, response, 
/*  81 */         servletContext);
/*     */     
/*  83 */     currentContext.set(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpServletRequest getRequest() {
/*  93 */     return (currentContext.get() == null) ? null : 
/*  94 */       ((HttpRequestContext)currentContext.get()).request;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpServletResponse getResponse() {
/* 104 */     return (currentContext.get() == null) ? null : 
/* 105 */       ((HttpRequestContext)currentContext.get()).response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServletContext getServletContext() {
/* 115 */     return (currentContext.get() == null) ? null : 
/* 116 */       ((HttpRequestContext)currentContext.get()).servletContext;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\web\common\httpequest\HttpRequestContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */