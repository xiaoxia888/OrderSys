/*     */ package com.chinasofti.util.web.serverpush;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ public class GetExchangeRateServlet
/*     */   extends HttpServlet
/*     */ {
/*  40 */   static Vector clients = new Vector();
/*     */ 
/*     */ 
/*     */   
/*  44 */   String messageTitleParameterName = "messageTitle";
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
/*  62 */     doPost(request, response);
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
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  80 */     request.setCharacterEncoding("utf-8");
/*     */     
/*  82 */     response.setCharacterEncoding("utf-8");
/*     */     
/*  84 */     HttpSession session = request.getSession(true);
/*     */     
/*  86 */     if (!clients.contains(session.getId()))
/*     */     {
/*  88 */       clients.add(session.getId());
/*     */     }
/*     */ 
/*     */     
/*  92 */     String messageTitle = request.getParameter(this.messageTitleParameterName);
/*     */     
/*  94 */     MessageConsumer mconsumer = new MessageConsumer();
/*     */ 
/*     */     
/*  97 */     final HttpServletResponse rsp = response;
/*     */     
/*  99 */     MessageHandler handler = new MessageHandler()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
/*     */         {
/*     */           try {
/* 110 */             PrintWriter pw = rsp.getWriter();
/*     */             
/* 112 */             pw.print(msg.getMsg());
/* 113 */           } catch (Exception ex) {
/*     */             
/* 115 */             ex.printStackTrace();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 120 */     mconsumer.searchMessage(session.getId(), messageTitle, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws ServletException {
/* 128 */     ServletConfig config = getServletConfig();
/*     */     
/* 130 */     if (config.getInitParameter("MessageTitleParameterName") != null)
/* 131 */       this.messageTitleParameterName = config
/* 132 */         .getInitParameter("MessageTitleParameterName"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\GetExchangeRateServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */