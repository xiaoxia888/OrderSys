/*     */ package com.chinasofti.util.jdbc.template.specialsqloperation;
/*     */ 
/*     */ import java.sql.PreparedStatement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OracleSpecialOperation
/*     */   extends SpecialSQLOperation
/*     */ {
/*     */   public String getTopNSQL(String initialSQL, boolean hasOffset) {
/*  39 */     initialSQL = initialSQL.trim();
/*     */     
/*  41 */     boolean isForUpdate = false;
/*     */     
/*  43 */     if (initialSQL.toLowerCase().endsWith(" for update")) {
/*     */       
/*  45 */       initialSQL = initialSQL.substring(0, initialSQL.length() - 11);
/*     */       
/*  47 */       isForUpdate = true;
/*     */     } 
/*     */     
/*  50 */     StringBuffer pagingSelect = new StringBuffer(initialSQL.length() + 200);
/*     */     
/*  52 */     if (hasOffset) {
/*     */       
/*  54 */       pagingSelect
/*  55 */         .append("select * from ( select row_.*, rownum rownum_ from ( ");
/*     */     }
/*     */     else {
/*     */       
/*  59 */       pagingSelect.append("select * from ( ");
/*     */     } 
/*     */     
/*  62 */     pagingSelect.append(initialSQL);
/*     */     
/*  64 */     if (hasOffset) {
/*     */       
/*  66 */       pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
/*     */     }
/*     */     else {
/*     */       
/*  70 */       pagingSelect.append(" ) where rownum <= ?");
/*     */     } 
/*     */     
/*  73 */     if (isForUpdate)
/*     */     {
/*  75 */       pagingSelect.append(" for update");
/*     */     }
/*     */     
/*  78 */     return pagingSelect.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PreparedStatement setTopNQueryParameter(PreparedStatement topNStatement, Object[] args, int offset, int size) {
/*     */     try {
/* 101 */       for (int i = 0; i < args.length; i++)
/*     */       {
/* 103 */         topNStatement.setObject(i + 1, args[i]);
/*     */       }
/*     */       
/* 106 */       topNStatement.setObject(args.length + 1, Integer.valueOf(size + size));
/*     */       
/* 108 */       topNStatement.setObject(args.length + 2, Integer.valueOf(offset));
/*     */ 
/*     */       
/* 111 */       return topNStatement;
/*     */     }
/* 113 */     catch (Exception e) {
/*     */       
/* 115 */       e.printStackTrace();
/*     */       
/* 117 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\specialsqloperation\OracleSpecialOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */