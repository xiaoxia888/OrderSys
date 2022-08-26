/*    */ package com.chinasofti.ordersys.vo;
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
/*    */ public class CartUnit
/*    */ {
/*    */   private int dishesId;
/*    */   private int num;
/*    */   
/*    */   private CartUnit(int dishesId, int num) {
/* 18 */     this.dishesId = dishesId;
/* 19 */     this.num = num;
/*    */   }
/*    */   
/*    */   public int getDishesId() {
/* 23 */     return this.dishesId;
/*    */   }
/*    */   
/*    */   public void setDishesId(int dishesId) {
/* 27 */     this.dishesId = dishesId;
/*    */   }
/*    */   
/*    */   public int getNum() {
/* 31 */     return this.num;
/*    */   }
/*    */   
/*    */   public void setNum(int num) {
/* 35 */     this.num = num;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\vo\CartUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */