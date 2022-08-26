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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessageConsumer
/*    */ {
/*    */   public void searchMessage(String sessionID, String messageTitle, MessageHandler handler) {
/* 38 */     Message msg = new Message();
/*    */     
/* 40 */     ServerPushKey key = new ServerPushKey(sessionID, messageTitle);
/*    */     
/* 42 */     if (ServerPushMQ.waitQueue.get(key) != null) {
/*    */       
/* 44 */       Message old = ServerPushMQ.waitQueue.get(key);
/*    */       
/* 46 */       old.setMsg("Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut");
/*    */       
/* 48 */       ServerPushMQ.waitQueue.remove(key);
/*    */       
/* 50 */       synchronized (old) {
/*    */         
/* 52 */         old.notifyAll();
/*    */       } 
/*    */     } 
/*    */     
/* 56 */     synchronized (msg) {
/*    */       
/*    */       try {
/* 59 */         ServerPushMQ.waitQueue.put(key, msg);
/*    */         
/* 61 */         msg.wait();
/*    */         
/* 63 */         handler.handle(ServerPushMQ.waitQueue, key, msg);
/*    */         
/* 65 */         if (!msg.getMsg().equals("Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut")) {
/* 66 */           ServerPushMQ.waitQueue.remove(key);
/*    */         }
/* 68 */       } catch (InterruptedException e) {
/*    */         
/* 70 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\MessageConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */