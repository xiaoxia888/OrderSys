/*    */ package com.chinasofti.ordersys.servlets.common;
/*    */ 
/*    */ import com.chinasofti.util.web.serverpush.MessageProducer;
/*    */ import com.chinasofti.util.web.upload.FormFile;
/*    */ import com.chinasofti.util.web.upload.MultipartRequestParser;
/*    */ import java.io.IOException;
import java.util.UUID;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
/*    */ public class PreviewImageUploadServlet
/*    */   extends HttpServlet
/*    */ {
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
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 62 */     String savepath = "/img/faces";
/*    */     
/* 64 */     if (request.getParameter("path") != null)
/*    */     {
/* 66 */       savepath = request.getParameter("path");
/*    */     }
/*    */     
/* 69 */     MultipartRequestParser parser = new MultipartRequestParser();
/*    */     
/* 71 */     PreviewImageInfo info = (PreviewImageInfo)parser.parse(request, 
/* 72 */         "com.chinasofti.ordersys.servlets.common.PreviewImageInfo");
/*    */
/* 74 */     FormFile img = info.getUploadFile();

    /*System.out.println(img.getFileName());
    System.out.println(img.getContextType());*/
/*    */     
/* 76 */     //String path = getServletContext().getRealPath(savepath);
    String path = "D:/store"+savepath;
    //String path = "D:/workspace/project/order1.0/src/main/webapp"+savepath;

    //System.out.println(path);
    int i = img.getFileName().lastIndexOf('.');
    String substring = img.getFileName().substring(i);
    /*    */     String fileName = UUID.randomUUID().toString().replace("-","")+substring;
/* 78 */     img.saveToFileSystem(request, String.valueOf(path) + "/" + fileName);
/*    */
    //发送修改后文件名
/*    */

     request.setCharacterEncoding("utf-8");



         MessageProducer producer = new MessageProducer();




         producer.sendMessage(request.getSession().getId().toString(),
             "upstate", /*img.getFileName()*/fileName);

       }
     }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\common\PreviewImageUploadServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */