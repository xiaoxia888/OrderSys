/*    */ package com.chinasofti.ordersys.service.login;
/*    */ 
/*    */ import com.chinasofti.ordersys.vo.UserInfo;
/*    */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
/*    */ import com.chinasofti.util.sec.Passport;
/*    */ import java.util.ArrayList;
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
/*    */ 
/*    */ 
/*    */ public class CheckUserPassService
/*    */ {
/*    */   public boolean checkPass(UserInfo info) {
/*    */     Passport passport;
/* 40 */     JDBCTemplateWithDS dbHelper = JDBCTemplateWithDS.getJDBCHelper();
/*    */     
/* 42 */     ArrayList<UserInfo> userList = dbHelper
/* 43 */       .preparedQueryForList(
/* 44 */         "select userId,userAccount,userPass,locked,roleId,roleName from userinfo,roleinfo where userinfo.role=roleinfo.roleId and userinfo.userId=?", 
/* 45 */         new Object[] { new Integer(info.getUserId())
/* 46 */         }, UserInfo.class);
/*    */     
/* 48 */     switch (userList.size()) {
/*    */ 
/*    */       
/*    */       case 0:
/* 52 */         return false;
/*    */ 
/*    */       
/*    */       case 1:
/* 56 */         passport = new Passport();
/*    */         
/* 58 */         if (((UserInfo)userList.get(0)).getUserPass()
/* 59 */           .equals(passport.md5(info.getUserPass())))
/*    */         {
/* 61 */           return true;
/*    */         }
/*    */ 
/*    */         
/* 65 */         return false;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\login\CheckUserPassService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */