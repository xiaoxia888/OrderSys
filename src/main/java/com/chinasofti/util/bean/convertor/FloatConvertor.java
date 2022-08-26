/*    */ package com.chinasofti.util.bean.convertor;
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
/*    */ public class FloatConvertor
/*    */   implements TypeConvertor
/*    */ {
/*    */   public Object convertToObject(Object srcString) {
/* 33 */     return new Float(srcString.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\convertor\FloatConvertor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */