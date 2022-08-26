/*     */ package com.chinasofti.util.web.upload;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.nio.channels.FileChannel;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class FormFile
/*     */ {
/*     */   private String fileName;
/*     */   private String contextType;
/*     */   private File tempFile;
/*     */   
/*     */   public String getFileName() {
/*  52 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFileName(String fileName) {
/*  62 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContextType() {
/*  71 */     return this.contextType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setContextType(String contextType) {
/*  81 */     this.contextType = contextType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   File getTempFile() {
/*  90 */     return this.tempFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTempFile(File tempFile) {
/* 100 */     this.tempFile = tempFile;
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
/*     */   public void saveToFileSystem(HttpServletRequest request, String path) {
/*     */     try {
/* 114 */       if (request != null) {
/*     */         
/* 116 */         HttpSession session = request.getSession(true);
/*     */         
/* 118 */         session.setAttribute("ICSS_UTIL_WEB_UPLOAD_STATE", 
/* 119 */             "ICSS_UTIL_WEB_UPLOAD_SAVE");
/*     */         
/* 121 */         session.setAttribute("ICSS_UTIL_WEB_UPLOAD_SAVING_FILENAME", 
/* 122 */             this.fileName);
/*     */       } 
/*     */       
/* 125 */       File distFile = new File(path);
/*     */       
/* 127 */       FileInputStream fis = new FileInputStream(this.tempFile);
/*     */       
/* 129 */       FileOutputStream fos = new FileOutputStream(distFile);
/*     */       
/* 131 */       FileChannel inChannel = fis.getChannel();
/*     */       
/* 133 */       FileChannel outChannel = fos.getChannel();
/*     */       
/* 135 */       inChannel.transferTo(0L, inChannel.size() - 2L, outChannel);
/*     */       
/* 137 */       inChannel.close();
/*     */       
/* 139 */       outChannel.close();
/*     */       
/* 141 */       fis.close();
/*     */       
/* 143 */       fos.close();
/*     */       
/* 145 */       this.tempFile.delete();
/*     */     }
/* 147 */     catch (Exception ex) {
/*     */       
/* 149 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\we\\upload\FormFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */