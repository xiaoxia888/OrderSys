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
/*    */ public class ServerPushKey
/*    */ {
/* 28 */   String sessionID = "";
/*    */ 
/*    */ 
/*    */   
/* 32 */   String messageTitle = "";
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
/*    */   public ServerPushKey(String sessionID, String messageTitle) {
/* 44 */     this.sessionID = sessionID;
/* 45 */     this.messageTitle = messageTitle;
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
/*    */   public boolean equals(Object arg0) {
/* 57 */     boolean isEquals = false;
/*    */     
/* 59 */     if (arg0 instanceof ServerPushKey) {
/*    */       
/* 61 */       ServerPushKey key = (ServerPushKey)arg0;
/*    */       
/* 63 */       if (key.sessionID.equals(this.sessionID) && 
/* 64 */         key.messageTitle.equals(this.messageTitle)) {
/* 65 */         isEquals = true;
/*    */       }
/*    */     } 
/*    */     
/* 69 */     return isEquals;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 80 */     return this.sessionID.hashCode() + this.messageTitle.hashCode();
/*    */   }
/*    */ }
