/*     */ package com.chinasofti.ordersys.service.waiters;
/*     */ 
/*     */ import com.chinasofti.ordersys.vo.Cart;
/*     */ import com.chinasofti.ordersys.vo.OrderInfo;
/*     */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class OrderService
/*     */ {
/*     */   public Object addOrder(int waiterId, int tableId) {
/*  43 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  45 */     Date now = new Date();
/*     */     
/*  47 */     Object[] key = helper
/*  48 */       .preparedInsertForGeneratedKeys(
/*  49 */         "insert into orderinfo(orderBeginDate,waiterId,tableId) values(?,?,?)", 
/*  50 */         new Object[] { now, new Integer(waiterId), 
/*  51 */           new Integer(tableId) });
/*     */     
/*  53 */     return key[0];
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
/*     */   public void addOrderDishesMap(Cart.CartUnit unit, Object key) {
/*  66 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  68 */     helper.executePreparedUpdate(
/*  69 */         "insert into orderdishes(orderReference,dishes,num) values(?,?,?)", 
/*  70 */         new Object[] { key, new Integer(unit.getDishesId()), 
/*  71 */           new Integer(unit.getNum()) });
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
/*     */   public ArrayList<OrderInfo> getNeedPayOrdersByPage(int page, int pageSize, int state) {
/*  89 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     ArrayList<OrderInfo> list = helper
/*  97 */       .preparedForPageList(
/*  98 */         "select * from orderinfo,userInfo where orderState=? and userInfo.userId=orderinfo.waiterId", 
/*  99 */         new Object[] { new Integer(state) }, page, pageSize, 
/* 100 */         OrderInfo.class);
/*     */     
/* 102 */     return list;
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
/*     */   public int getMaxPage(int pageSize, int state) {
/* 117 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 119 */     Long rows = (Long)helper.preparedQueryForObject(
/* 120 */         "select count(*) from orderinfo where orderState=?", 
/* 121 */         new Object[] { new Integer(state) });
/*     */     
/* 123 */     return (int)((rows.longValue() - 1L) / pageSize + 1L);
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
/*     */   public ArrayList<OrderInfo> getNeedPayOrders(int state) {
/* 135 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 137 */     ArrayList<OrderInfo> list = helper
/* 138 */       .preparedQueryForList(
/* 139 */         "select * from orderinfo,userInfo where orderState=? and userInfo.userId=orderinfo.waiterId", 
/* 140 */         new Object[] { new Integer(state) }, OrderInfo.class);
/*     */     
/* 142 */     return list;
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
/*     */   public void requestPay(Integer orderId) {
/* 154 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 156 */     Date now = new Date();
/*     */     
/* 158 */     helper.executePreparedUpdate(
/* 159 */         "update orderinfo set orderState=1,orderEndDate=? where orderId=?", 
/* 160 */         new Object[] { now, new Integer(orderId.intValue()) });
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
/*     */   public OrderInfo getOrderById(Integer orderId) {
/* 173 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 175 */     return helper
/* 176 */       .preparedQueryForList(
/* 177 */         "select * from orderinfo,userinfo where orderId=? and orderinfo.waiterId=userinfo.userId", 
/* 178 */         new Object[] { orderId }, OrderInfo.class).get(0);
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
/*     */   public float getSumPriceByOrderId(Integer orderId) {
/* 190 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 192 */     Double sum = (Double)helper
/* 193 */       .preparedQueryForObject(
/* 194 */         "SELECT SUM(d.dishesPrice*od.num) FROM orderinfo a,dishesinfo d,orderdishes od WHERE a.orderId=od.orderReference AND od.dishes=d.dishesId AND a.orderId=?", 
/* 195 */         new Object[] { orderId });
/*     */     
/* 197 */     return sum.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<OrderInfo> getOrderDetailById(Integer orderId) {
/* 208 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 210 */     return helper
/* 211 */       .preparedQueryForList(
/* 212 */         "SELECT * FROM orderinfo o,userinfo u,orderdishes od,dishesinfo d WHERE orderId=? AND o.waiterId=u.userId AND od.orderReference=o.orderId AND d.dishesId=od.dishes", 
/* 213 */         new Object[] { orderId }, OrderInfo.class);
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
/*     */   public void changeState(Integer orderId, int state) {
/* 227 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 229 */     helper.executePreparedUpdate(
/* 230 */         "update orderinfo set orderState=? where orderId=?", 
/* 231 */         new Object[] { new Integer(state), orderId });
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
/*     */   public ArrayList<OrderInfo> getOrderInfoBetweenDate(Date beginDate, Date endDate) {
/* 247 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 249 */     ArrayList<OrderInfo> list = helper
/* 250 */       .preparedQueryForList(
/* 251 */         "select * from orderinfo,userInfo where orderState=2 and userInfo.userId=orderinfo.waiterId and orderinfo.orderEndDate between ? and ?", 
/* 252 */         new Object[] { beginDate, endDate }, OrderInfo.class);
/*     */     
/* 254 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\waiters\OrderService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */