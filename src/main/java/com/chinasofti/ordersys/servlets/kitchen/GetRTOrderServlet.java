/*     */ package com.chinasofti.ordersys.servlets.kitchen;
/*     */ 
/*     */ import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
/*     */ import com.chinasofti.util.web.serverpush.Message;
/*     */ import com.chinasofti.util.web.serverpush.MessageHandler;
/*     */ import com.chinasofti.util.web.serverpush.ServerPushKey;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
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
/*     */ public class GetRTOrderServlet
/*     */   extends BaseGetPushMsgServlet
/*     */ {
/*  40 */   public static ArrayList<String> kitchens = new ArrayList<>();
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
/*     */   public MessageHandler getHandler(HttpServletRequest request, HttpServletResponse response) {
/*  56 */     response.setCharacterEncoding("utf-8");
/*     */ 
/*     */     
/*     */     try {
/*  60 */       final PrintWriter out = response.getWriter();
/*     */       
/*  62 */       MessageHandler handler = new MessageHandler()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
/*     */           {
/*  69 */             out.print(msg.getMsg());
/*     */           }
/*     */         };
/*     */ 
/*     */ 
/*     */       
/*  75 */       return handler;
/*     */     }
/*  77 */     catch (Exception ex) {
/*     */       
/*  79 */       ex.printStackTrace();
/*     */       
/*  81 */       return null;
/*     */     } 
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
/*     */   public void initService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
/* 100 */     kitchens.add(session.getId());
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\kitchen\GetRTOrderServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */