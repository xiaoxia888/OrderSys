/*    */ package com.chinasofti.ordersys.servlets.login;
/*    */ 
/*    */ import com.chinasofti.ordersys.vo.UserInfo;
/*    */ import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.ArrayList;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckAddUserServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 49 */     doPost(request, response);
/*    */   }
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
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 64 */     String userAccount = request.getParameter("name");
/*    */     
/* 66 */     userAccount = new String(userAccount.getBytes("iso8859-1"), "utf-8");
/*    */     
/* 68 */     JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
/*    */     
/* 70 */     ArrayList<UserInfo> list = helper.preparedQueryForList(
/* 71 */         "select userAccount from userinfo where userAccount=?", 
/* 72 */         new Object[] { userAccount }, UserInfo.class);
/*    */     
/* 74 */     PrintWriter pw = response.getWriter();
/*    */     
/* 76 */     if (list.size() == 0) {
/*    */       
/* 78 */       pw.print("OK");
/*    */     }
/*    */     else {
/*    */       
/* 82 */       pw.print("FAIL");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\CheckAddUserServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */