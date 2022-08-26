/*    */ package com.chinasofti.web.servlets.common;
/*    */ 
/*    */ import com.chinasofti.web.common.service.SaveCodeService;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
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
/*    */ public class SaveCodeServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public static final String CODE_SESSION_ATTR_NAME = "web_app_savecode_value";
/*    */   
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 54 */     doPost(request, response);
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
/* 69 */     response.setHeader("Pragma", "No-cache");
/*    */     
/* 71 */     response.setHeader("Cache-Control", "no-cache");
/*    */     
/* 73 */     response.setDateHeader("Expires", 0L);
/*    */     
/* 75 */     response.setContentType("image/jpeg");
/*    */     
/* 77 */     SaveCodeService codeService = new SaveCodeService(
/* 78 */         "abcdefghijklmnopqrstuvwxyz123456789".toUpperCase()
/* 79 */         .toCharArray(), 100, 25, 6);
/*    */     
/* 81 */     codeService.createSaveCodeImage();
/*    */     
/* 83 */     BufferedImage img = codeService.getImage();
/*    */     
/* 85 */     String codeString = codeService.getCodeString();
/*    */     
/* 87 */     HttpSession session = request.getSession();
/*    */     
/* 89 */     session.setAttribute("web_app_savecode_value", codeString);
/*    */     
/*    */     try {
/* 92 */       ImageIO.write(img, "JPEG", (OutputStream)response.getOutputStream());
/*    */     }
/* 94 */     catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\web\servlets\common\SaveCodeServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */