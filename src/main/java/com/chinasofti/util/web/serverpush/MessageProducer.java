/*    */ package com.chinasofti.util.web.serverpush;
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
/*    */ public class MessageProducer
/*    */ {
/*    */   public void sendMessage(String sessionID, String messageTitle, String msg) {
/* 35 */     ServerPushKey key = new ServerPushKey(sessionID, messageTitle);
/*    */     
/* 37 */     if (ServerPushMQ.waitQueue.get(key) != null) {
/*    */       
/* 39 */       Message message = ServerPushMQ.waitQueue.get(key);
/*    */
        System.out.println("MessageProducer"+msg);
/* 41 */       message.setMsg(msg);
/*    */       
/* 43 */       synchronized (message) {
/*    */         
/* 45 */         message.notifyAll();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\MessageProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */