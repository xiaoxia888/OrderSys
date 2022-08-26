/*    */ package com.chinasofti.util.jdbc.datasource;
/*    */ 
/*    */ import java.lang.reflect.InvocationHandler;
/*    */ import java.lang.reflect.Method;
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
/*    */ public class DSConnectionInvocationHandler
/*    */   implements InvocationHandler
/*    */ {
/*    */   DSConnectionContext conn;
/*    */   
/*    */   public DSConnectionInvocationHandler(DSConnectionContext conn) {
/* 28 */     this.conn = conn;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 44 */     Object resultObject = null;
/*    */     
/* 46 */     if ("close".equals(method.getName())) {
/*    */       
/* 48 */       this.conn.busyFlag = false;
/*    */     } else {
/*    */       
/* 51 */       resultObject = method.invoke(this.conn.dbConnection, args);
/*    */     } 
/*    */     
/* 54 */     return resultObject;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\datasource\DSConnectionInvocationHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */