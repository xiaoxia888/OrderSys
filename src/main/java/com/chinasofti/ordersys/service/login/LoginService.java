/*     */ package com.chinasofti.ordersys.service.login;
/*     */ 
/*     */ import com.chinasofti.ordersys.listeners.OrderSysListener;
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginService
/*     */ {
/*     */   public static final int WRONG_USERNAME = 0;
/*     */   public static final int WRONG_PASSWORD = 1;
/*     */   public static final int USER_ALREADY_ONLINE = 2;
/*     */   public static final int WRONG_LOCKED = 3;
/*     */   public static final int WRONG_OTHER = 4;
/*     */   public static final int LOGIN_OK = 5;
/*  62 */   private UserInfo loginUser = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserInfo getLoginUser() {
/*  71 */     return this.loginUser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int login(UserInfo info) {
/*     */     UserInfo dbUser;
/*  83 */     Hashtable<String, UserInfo> loginUserMap = OrderSysListener.sessions;
/*     */     
/*  85 */     Set<String> loginIds = loginUserMap.keySet();
/*     */     
/*  87 */     Iterator<String> it = loginIds.iterator();
/*     */     
/*  89 */     while (it.hasNext()) {
/*     */       
/*  91 */       UserInfo user = loginUserMap.get(it.next());
/*     */       
/*  93 */       if (user.getUserAccount().equals(info.getUserAccount()))
/*     */       {
/*  95 */         return 2;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     JDBCTemplateWithDS dbHelper = JDBCTemplateWithDS.getJDBCHelper();
/*     */     
/* 101 */     ArrayList<UserInfo> userList = dbHelper
/* 102 */       .preparedQueryForList(
/* 103 */         "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId and userinfo.userAccount=?", 
/* 104 */         new Object[] { info.getUserAccount() }, UserInfo.class);
/*     */     
/* 106 */     switch (userList.size()) {
/*     */ 
/*     */       
/*     */       case 0:
/* 110 */         return 0;
/*     */ 
/*     */       
/*     */       case 1:
/* 114 */         dbUser = userList.get(0);
/*     */ 
/*     */ 
/*     */         
/* 118 */         if (dbUser.getLocked() == 1) {
/*     */           
/* 120 */           this.loginUser = dbUser;
/*     */           
/* 122 */           return 3;
/*     */         } 
/*     */         
/* 125 */         if (info.getUserPass().equals(dbUser.getUserPass())) {
/*     */           
/* 127 */           this.loginUser = dbUser;
/*     */           
/* 129 */           return 5;
/*     */         } 
/*     */ 
/*     */         
/* 133 */         return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 138 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\login\LoginService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */