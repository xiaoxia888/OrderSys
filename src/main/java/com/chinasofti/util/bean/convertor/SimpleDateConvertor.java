/*    */ package com.chinasofti.util.bean.convertor;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
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
/*    */ public class SimpleDateConvertor
/*    */   implements TypeConvertor
/*    */ {
/*    */   public Object convertToObject(Object srcString) {
/* 38 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*    */     
/*    */     try {
/* 41 */       Date date = sdf.parse(srcString.toString());
/* 42 */       return date;
/* 43 */     } catch (ParseException e) {
/*    */       
/* 45 */       e.printStackTrace();
/* 46 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\convertor\SimpleDateConvertor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */