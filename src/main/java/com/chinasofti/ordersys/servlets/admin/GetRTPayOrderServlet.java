/*     */ package com.chinasofti.ordersys.servlets.admin;
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
/*     */ 
/*     */ public class GetRTPayOrderServlet
/*     */   extends BaseGetPushMsgServlet
/*     */ {
/*  41 */   public static ArrayList<String> pays = new ArrayList<>();
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
/*     */     
/*     */     try {
/*  61 */       final PrintWriter out = response.getWriter();
/*     */       
/*  63 */       MessageHandler handler = new MessageHandler()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
/*     */           {
/*  70 */             out.print(msg.getMsg());
/*     */           }
/*     */         };
/*     */ 
/*     */ 
/*     */       
/*  76 */       return handler;
/*     */     }
/*  78 */     catch (Exception ex) {
/*     */       
/*  80 */       ex.printStackTrace();
/*     */       
/*  82 */       return null;
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
/* 101 */     pays.add(session.getId());
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetRTPayOrderServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */