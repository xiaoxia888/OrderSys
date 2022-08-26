/*     */ package com.chinasofti.ordersys.vo;
/*     */ 
/*     */ import java.sql.Date;
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
/*     */ public class OrderInfo
/*     */ {
/*     */   private int orderId;
/*     */   private Date orderBeginDate;
/*     */   private Date orderEndDate;
/*     */   private int waiterId;
/*     */   private int tableId;
/*     */   private String userAccount;
/*     */   private float dishesPrice;
/*     */   private String dishesName;
/*     */   private int orderState;
/*     */   private int dishes;
/*     */   private int num;
/*     */   
/*     */   public String getDishesName() {
/*  60 */     return this.dishesName;
/*     */   }
/*     */   
/*     */   public void setDishesName(String dishesName) {
/*  64 */     this.dishesName = dishesName;
/*     */   }
/*     */   
/*     */   public float getDishesPrice() {
/*  68 */     return this.dishesPrice;
/*     */   }
/*     */   
/*     */   public void setDishesPrice(float dishesPrice) {
/*  72 */     this.dishesPrice = dishesPrice;
/*     */   }
/*     */   
/*     */   public String getUserAccount() {
/*  76 */     return this.userAccount;
/*     */   }
/*     */   
/*     */   public void setUserAccount(String userAccount) {
/*  80 */     this.userAccount = userAccount;
/*     */   }
/*     */   
/*     */   public int getTableId() {
/*  84 */     return this.tableId;
/*     */   }
/*     */   
/*     */   public void setTableId(int tableId) {
/*  88 */     this.tableId = tableId;
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
/*     */   public int getOrderId() {
/* 105 */     return this.orderId;
/*     */   }
/*     */   
/*     */   public void setOrderId(int orderId) {
/* 109 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getOrderBeginDate() {
/* 114 */     return this.orderBeginDate;
/*     */   }
/*     */   
/*     */   public void setOrderBeginDate(Date orderBeginDate) {
/* 118 */     this.orderBeginDate = orderBeginDate;
/*     */   }
/*     */   
/*     */   public Date getOrderEndDate() {
/* 122 */     return this.orderEndDate;
/*     */   }
/*     */   
/*     */   public void setOrderEndDate(Date orderEndDate) {
/* 126 */     this.orderEndDate = orderEndDate;
/*     */   }
/*     */   
/*     */   public int getWaiterId() {
/* 130 */     return this.waiterId;
/*     */   }
/*     */   
/*     */   public void setWaiterId(int waiterId) {
/* 134 */     this.waiterId = waiterId;
/*     */   }
/*     */   
/*     */   public int getOrderState() {
/* 138 */     return this.orderState;
/*     */   }
/*     */   
/*     */   public void setOrderState(int orderState) {
/* 142 */     this.orderState = orderState;
/*     */   }
/*     */   
/*     */   public int getDishes() {
/* 146 */     return this.dishes;
/*     */   }
/*     */   
/*     */   public void setDishes(int dishes) {
/* 150 */     this.dishes = dishes;
/*     */   }
/*     */   
/*     */   public int getNum() {
/* 154 */     return this.num;
/*     */   }
/*     */   
/*     */   public void setNum(int num) {
/* 158 */     this.num = num;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\vo\OrderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */