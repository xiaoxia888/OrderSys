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
/*    */ public class CharConvertor
/*    */   implements TypeConvertor
/*    */ {
/*    */   public Object convertToObject(Object srcString) {
/* 33 */     return new Character(srcString.toString().charAt(0));
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\convertor\CharConvertor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */