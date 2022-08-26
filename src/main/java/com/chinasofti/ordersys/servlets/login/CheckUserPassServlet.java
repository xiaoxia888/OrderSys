/*    */ package com.chinasofti.ordersys.servlets.login;
/*    */ 
/*    */ import com.chinasofti.ordersys.service.login.CheckUserPassService;
/*    */ import com.chinasofti.ordersys.vo.UserInfo;
/*    */ import com.chinasofti.util.web.upload.MultipartRequestParser;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
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
/*    */ public class CheckUserPassServlet
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
/* 63 */     MultipartRequestParser parser = new MultipartRequestParser();
/*    */     
/* 65 */     UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);
/*    */     
/* 67 */     CheckUserPassService service = new CheckUserPassService();
/*    */     
/* 69 */     PrintWriter pw = response.getWriter();
/*    */     
/* 71 */     if (service.checkPass(info)) {
/*    */       
/* 73 */       pw.print("OK");
/*    */     }
/*    */     else {
/*    */       
/* 77 */       pw.print("FAIL");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\CheckUserPassServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */