/*     */ package com.chinasofti.util.web.serverpush;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public abstract class BaseGetPushMsgServlet
/*     */   extends HttpServlet
/*     */ {
/*  37 */   String messageTitleParameterName = "messageTitle";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract MessageHandler getHandler(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {
/*  67 */     super.destroy();
/*     */   }
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
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  88 */     doPost(request, response);
/*     */   }
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
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 110 */     request.setCharacterEncoding("utf-8");
/*     */     
/* 112 */     response.setCharacterEncoding("utf-8");
/*     */ 
/*     */     
/* 115 */     HttpSession session = request.getSession(true);
/* 116 */     initService(request, response, session);
/*     */ 
/*     */     
/* 119 */     String messageTitle = request.getParameter(this.messageTitleParameterName);
/*     */     
/* 121 */     MessageConsumer mconsumer = new MessageConsumer();
/*     */ 
/*     */     
/* 124 */     HttpServletResponse rsp = response;
/*     */ 
/*     */     
/* 127 */     MessageHandler handler = getHandler(request, response);
/*     */ 
/*     */     
/* 130 */     mconsumer.searchMessage(session.getId(), messageTitle, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws ServletException {
/* 141 */     ServletConfig config = getServletConfig();
/*     */     
/* 143 */     if (config.getInitParameter("MessageTitleParameterName") != null)
/* 144 */       this.messageTitleParameterName = config
/* 145 */         .getInitParameter("MessageTitleParameterName"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\BaseGetPushMsgServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */