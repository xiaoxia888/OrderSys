/*    */ package com.chinasofti.util.web.serverpush;
/*    */ 
/*    */ import com.chinasofti.util.web.upload.FormFile;
/*    */ import com.chinasofti.util.web.upload.MultipartRequestParser;
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
/*    */ public class PreviewImageUploadServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void destroy() {
/* 27 */     super.destroy();
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
/*    */ 
/*    */   
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 48 */     doPost(request, response);
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
/*    */ 
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 69 */     MultipartRequestParser parser = new MultipartRequestParser();
/* 70 */     PreviewImageInfo info = (PreviewImageInfo)parser.parse(request, 
/* 71 */         "com.chinasofti.util.web.serverpush.PreviewImageInfo");
/* 72 */     FormFile img = info.getUploadFile();
/* 73 */     String path = getServletContext().getRealPath("/images");
/* 74 */     img.saveToFileSystem(request, String.valueOf(path) + "/" + img.getFileName());
/*    */ 
/*    */     
/* 77 */     request.setCharacterEncoding("utf-8");
/*    */ 
/*    */     
/* 80 */     MessageProducer producer = new MessageProducer();
/*    */ 
/*    */ 
/*    */     
/* 84 */     producer.sendMessage(request.getSession().getId().toString(), 
/* 85 */         "upstate", img.getFileName());
/*    */   }
/*    */   
/*    */   public void init() throws ServletException {}
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\PreviewImageUploadServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */