/*    */ package com.chinasofti.util.jdbc.template.specialsqloperation;
/*    */ 
/*    */ import java.sql.PreparedStatement;
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
/*    */ public class MySQLSpecialOperation
/*    */   extends SpecialSQLOperation
/*    */ {
/*    */   public String getTopNSQL(String initialSQL, boolean hasOffset) {
/* 39 */     return (new StringBuffer(initialSQL.length() + 50)).append(initialSQL)
/* 40 */       .append(hasOffset ? " limit ?, ?" : " limit ?").toString();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PreparedStatement setTopNQueryParameter(PreparedStatement topNStatement, Object[] args, int offset, int size) {
/*    */     try {
/* 62 */       for (int i = 0; i < args.length; i++)
/*    */       {
/* 64 */         topNStatement.setObject(i + 1, args[i]);
/*    */       }
/*    */       
/* 67 */       topNStatement.setObject(args.length + 1, Integer.valueOf(offset));
/*    */       
/* 69 */       topNStatement.setObject(args.length + 2, Integer.valueOf(size));
/*    */ 
/*    */       
/* 72 */       return topNStatement;
/*    */     }
/* 74 */     catch (Exception e) {
/*    */       
/* 76 */       e.printStackTrace();
/*    */       
/* 78 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\specialsqloperation\MySQLSpecialOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */