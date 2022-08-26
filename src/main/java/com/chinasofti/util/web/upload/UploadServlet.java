/*    */ package com.chinasofti.util.web.upload;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UploadServlet
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 68 */     MultipartRequestParser parser = new MultipartRequestParser();
/*    */     
/* 70 */     SubmitInformation info = (SubmitInformation)parser.parse(request, 
/* 71 */         "com.chinasofti.util.web.upload.SubmitInformation");
/*    */     
/* 73 */     /*System.out.println(info.getOwnerName());
*//*    *//*
*//* 75 *//*     System.out.println(info.getOwnerAge());*/
/*    */     
/* 77 */     info.getUploadFile().saveToFileSystem(
/* 78 */         request, 
/* 79 */         String.valueOf(getServletContext().getRealPath("/upload")) + "/" + 
/* 80 */         info.getUploadFile().getFileName());
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\we\\upload\UploadServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */