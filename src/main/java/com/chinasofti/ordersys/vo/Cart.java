/*     */ package com.chinasofti.ordersys.vo;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public class Cart
/*     */ {
/*     */   private int tableId;
/*     */   
/*     */   public int getTableId() {
/*  33 */     return this.tableId;
/*     */   }
/*     */   
/*     */   public void setTableId(int tableId) {
/*  37 */     this.tableId = tableId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private ArrayList<CartUnit> units = new ArrayList<>();
/*     */   
/*     */   public ArrayList<CartUnit> getUnits() {
/*  46 */     return this.units;
/*     */   }
/*     */   
/*     */   public void setUnits(ArrayList<CartUnit> units) {
/*  50 */     this.units = units;
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
/*     */   public CartUnit createUnit(int dishesId, int num) {
/*  63 */     return new CartUnit(dishesId, num);
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
/*     */   public class CartUnit
/*     */   {
/*     */     private int dishesId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int num;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private CartUnit(int dishesId, int num) {
/*  96 */       this.dishesId = dishesId;
/*  97 */       this.num = num;
/*     */     }
/*     */     
/*     */     public int getDishesId() {
/* 101 */       return this.dishesId;
/*     */     }
/*     */     
/*     */     public void setDishesId(int dishesId) {
/* 105 */       this.dishesId = dishesId;
/*     */     }
/*     */     
/*     */     public int getNum() {
/* 109 */       return this.num;
/*     */     }
/*     */     
/*     */     public void setNum(int num) {
/* 113 */       this.num = num;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\vo\Cart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */