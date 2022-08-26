/*    */ package com.chinasofti.ordersys.servlets.login;
/*    */ 
/*    */ import com.chinasofti.ordersys.service.admin.UserService;
/*    */ import com.chinasofti.ordersys.vo.UserInfo;
/*    */ import com.chinasofti.util.web.upload.MultipartRequestParser;
/*    */ import com.chinasofti.web.common.taglib.TokenTag;
/*    */ import java.io.IOException;
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
/*    */ public class AddUserServlet
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
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 63 */     //if (TokenTag.isTokenValid()) {
/*    */       
/* 65 */       MultipartRequestParser parser = new MultipartRequestParser();
/*    */       
/* 67 */       UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);
/*    */       
/* 69 */       UserService service = new UserService();
/*    */       
/* 71 */       service.addUser(info);
/*    */       
/* 73 */       TokenTag.releaseToken();
/*    */     //}
/*    */     
/* 76 */     response.sendRedirect("/OrderSys/touseradmin.order");
/*    */   }
/*    */   
/*    */   public void init() throws ServletException {}
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\AddUserServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */