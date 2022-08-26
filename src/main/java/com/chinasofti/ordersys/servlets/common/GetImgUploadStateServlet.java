/*    */ package com.chinasofti.ordersys.servlets.common;
/*    */ 
/*    */ import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
/*    */ import com.chinasofti.util.web.serverpush.Message;
/*    */ import com.chinasofti.util.web.serverpush.MessageHandler;
/*    */ import com.chinasofti.util.web.serverpush.ServerPushKey;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Hashtable;
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
/*    */ public class GetImgUploadStateServlet
/*    */   extends BaseGetPushMsgServlet
/*    */ {
/*    */   public MessageHandler getHandler(HttpServletRequest request, final HttpServletResponse response) {
/* 53 */     MessageHandler handler = new MessageHandler()
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         public void handle(Hashtable<ServerPushKey, Message> messageQueue, ServerPushKey key, Message msg)
/*    */         {
/*    */           try {
/* 64 */             PrintWriter pw = response.getWriter();
/*    */             
/* 66 */             pw.print(msg.getMsg());
/* 67 */           } catch (Exception ex) {
/*    */             
/* 69 */             ex.printStackTrace();
/*    */           } 
/*    */         }
/*    */       };
/*    */     
/* 74 */     return handler;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\common\GetImgUploadStateServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */