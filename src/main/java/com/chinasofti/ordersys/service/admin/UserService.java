/*     */ package com.chinasofti.ordersys.service.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
/*     */ import com.chinasofti.util.sec.Passport;
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
/*     */ 
/*     */ public class UserService
/*     */ {
/*     */   public ArrayList<UserInfo> getByPage(int page, int pageSize) {
/*  42 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  44 */     ArrayList<UserInfo> list = helper
/*  45 */       .preparedForPageList(
/*  46 */         "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId order by userId", 
/*  47 */         new Object[0], page, pageSize, UserInfo.class);
/*     */     
/*  49 */     return list;
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
/*     */   public int getMaxPage(int pageSize) {
/*  61 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  63 */     Long rows = (Long)helper.preparedQueryForObject(
/*  64 */         "select count(*) from userinfo", new Object[0]);
/*     */     
/*  66 */     return (int)((rows.longValue() - 1L) / pageSize + 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUser(UserInfo info) {
/*  77 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  79 */     Passport passport = new Passport();
/*     */     
/*  81 */     helper.executePreparedUpdate(
/*  82 */         "insert into userinfo(userAccount,userPass,role,faceImg) values(?,?,?,?)", 
/*  83 */         new Object[] { info.getUserAccount(), 
/*  84 */           passport.md5(info.getUserPass()), 
/*  85 */           new Integer(info.getRoleId()), info.getFaceimg() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteUser(Integer userId) {
/*  96 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/*  98 */     helper.executePreparedUpdate("delete from userinfo where userId=?", 
/*  99 */         new Object[] { userId });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void modify(UserInfo info) {
/* 110 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 112 */     Passport passport = new Passport();
/*     */     
/* 114 */     helper.executePreparedUpdate(
/* 115 */         "update userinfo set userPass=?,faceimg=? where userId=?", 
/* 116 */         new Object[] { passport.md5(info.getUserPass()), 
/* 117 */           info.getFaceimg(), new Integer(info.getUserId()) });
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
/*     */   public void adminModify(UserInfo info) {
/* 129 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 131 */     Passport passport = new Passport();
/*     */     
/* 133 */     helper.executePreparedUpdate(
/* 134 */         "update userinfo set userPass=?,faceimg=?,role=? where userId=?", 
/* 135 */         new Object[] { passport.md5(info.getUserPass()), 
/* 136 */           info.getFaceimg(), new Integer(info.getRoleId()), 
/* 137 */           new Integer(info.getUserId()) });
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
/*     */   public UserInfo getUserById(Integer userId) {
/* 150 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 152 */     ArrayList<UserInfo> list = helper
/* 153 */       .preparedQueryForList(
/* 154 */         "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId and userId=?", 
/* 155 */         new Object[] { userId }, UserInfo.class);
/*     */     
/* 157 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\admin\UserService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */