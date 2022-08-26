/*     */ package com.chinasofti.ordersys.service.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.vo.DishesInfo;
/*     */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DishesService
/*     */ {
/*     */   public ArrayList<DishesInfo> getDishesInfoByPage(int page, int pageSize) {
/*  40 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  42 */     ArrayList<DishesInfo> list = helper.preparedForPageList(
/*  43 */         "select * from dishesinfo order by recommend desc,dishesId", 
/*  44 */         new Object[0], page, pageSize, DishesInfo.class);
/*     */     
/*  46 */     return list;
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
/*     */   public int getMaxPage(int pageSize) {
/*  59 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  61 */     Long rows = (Long)helper.preparedQueryForObject(
/*  62 */         "select count(*) from dishesinfo", new Object[0]);
/*     */     
/*  64 */     return (int)((rows.longValue() - 1L) / pageSize + 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteDishesById(Integer dishesId) {
/*  75 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  77 */     helper.executePreparedUpdate("delete from dishesinfo where dishesId=?", 
/*  78 */         new Object[] { dishesId });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDishes(DishesInfo info) {
/*  89 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  91 */     helper.executePreparedUpdate(
/*  92 */         "insert into dishesinfo(dishesName,dishesDiscript,dishesTxt,dishesImg,recommend,dishesPrice) values(?,?,?,?,?,?)", 
/*  93 */         new Object[] { info.getDishesName(), info.getDishesDiscript(), 
/*  94 */           info.getDishesTxt(), info.getDishesImg(), 
/*  95 */           new Integer(info.getRecommend()), 
/*  96 */           new Float(info.getDishesPrice()) });
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
/*     */   public DishesInfo getDishesById(Integer dishesId) {
/* 109 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 111 */     ArrayList<DishesInfo> list = helper.preparedQueryForList(
/* 112 */         "select * from dishesinfo where dishesId=?", 
/* 113 */         new Object[] { dishesId }, DishesInfo.class);
/*     */     
/* 115 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void modifyDishes(DishesInfo info) {
/* 126 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 128 */     helper.executePreparedUpdate(
/* 129 */         "update dishesinfo set dishesName=?,dishesDiscript=?,dishesTxt=?,dishesImg=?,recommend=?,dishesPrice=? where dishesId=?", 
/* 130 */         new Object[] { info.getDishesName(), info.getDishesDiscript(), 
/* 131 */           info.getDishesTxt(), info.getDishesImg(), 
/* 132 */           new Integer(info.getRecommend()), 
/* 133 */           new Float(info.getDishesPrice()), 
/* 134 */           new Integer(info.getDishesId()) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<DishesInfo> getTop4RecommendDishes() {
/* 145 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 147 */     ArrayList<DishesInfo> list = helper.preparedForPageList(
/* 148 */         "select * from dishesinfo where recommend=1 order by dishesId", 
/* 149 */         new Object[0], 1, 4, DishesInfo.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\admin\DishesService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */