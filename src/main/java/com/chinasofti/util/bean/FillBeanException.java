/*    */ package com.chinasofti.util.bean;
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
/*    */ public class FillBeanException
/*    */   extends RuntimeException
/*    */ {
/*    */   public FillBeanException(String beanClassName, String propertyName) {
/* 33 */     super("填充Bean类" + beanClassName + "的属性" + propertyName + "时发生异常");
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\FillBeanException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */