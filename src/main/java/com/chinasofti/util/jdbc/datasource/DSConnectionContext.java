/*    */ package com.chinasofti.util.jdbc.datasource;
/*    */ 
/*    */ import java.sql.Connection;
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
/*    */ public class DSConnectionContext
/*    */ {
/*    */   Connection dbConnection;
/*    */   Connection proxyConnection;
/*    */   boolean busyFlag;
/*    */   
/*    */   DSConnectionContext(Connection dbConnection) {
/* 36 */     this.busyFlag = false;
/*    */     
/* 38 */     this.dbConnection = dbConnection;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\datasource\DSConnectionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */