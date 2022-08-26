/*    */ package com.chinasofti.util.web.serverpush;
/*    */ 
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
/*    */ public class GetImgUploadStateServlet
/*    */   extends BaseGetPushMsgServlet
/*    */ {
/*    */   public MessageHandler getHandler(HttpServletRequest request, final HttpServletResponse response) {
/* 40 */     MessageHandler handler = new MessageHandler()
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
/* 51 */             PrintWriter pw = response.getWriter();
/*    */             
/* 53 */             pw.print(msg.getMsg());
/* 54 */           } catch (Exception ex) {
/*    */             
/* 56 */             ex.printStackTrace();
/*    */           } 
/*    */         }
/*    */       };
/* 60 */     return handler;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\GetImgUploadStateServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */