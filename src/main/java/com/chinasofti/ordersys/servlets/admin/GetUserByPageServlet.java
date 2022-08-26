/*     */ package com.chinasofti.ordersys.servlets.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.admin.UserService;
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class GetUserByPageServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  55 */     doPost(request, response);
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
/*     */   
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  69 */     response.setContentType("text/xml");
/*     */     
/*  71 */     int page = Integer.parseInt(request.getParameter("page"));
/*     */     
/*  73 */     UserService service = new UserService();
/*     */     
/*  75 */     int maxPage = service.getMaxPage(10);
/*     */     
/*  77 */     page = (page < 1) ? 1 : page;
/*     */     
/*  79 */     page = (page > maxPage) ? maxPage : page;
/*     */     
/*  81 */     ArrayList<UserInfo> list = service.getByPage(page, 10);
/*     */ 
/*     */     
/*     */     try {
/*  85 */       Document doc = DocumentBuilderFactory.newInstance()
/*  86 */         .newDocumentBuilder().newDocument();
/*     */       
/*  88 */       Element root = doc.createElement("users");
/*     */       
/*  90 */       doc.appendChild(root);
/*     */       
/*  92 */       for (UserInfo info : list) {
/*     */         
/*  94 */         Element user = doc.createElement("user");
/*     */         
/*  96 */         Element userId = doc.createElement("userId");
/*     */         
/*  98 */         userId.setTextContent((new StringBuilder(String.valueOf(info.getUserId()))).toString());
/*     */         
/* 100 */         user.appendChild(userId);
/*     */         
/* 102 */         Element userAccount = doc.createElement("userAccount");
/*     */         
/* 104 */         userAccount.setTextContent(info.getUserAccount());
/*     */         
/* 106 */         user.appendChild(userAccount);
/*     */         
/* 108 */         Element roleId = doc.createElement("roleId");
/*     */         
/* 110 */         roleId.setTextContent((new StringBuilder(String.valueOf(info.getRoleId()))).toString());
/*     */         
/* 112 */         user.appendChild(roleId);
/*     */         
/* 114 */         Element roleName = doc.createElement("roleName");
/*     */         
/* 116 */         roleName.setTextContent(info.getRoleName());
/*     */         
/* 118 */         user.appendChild(roleName);
/*     */         
/* 120 */         Element locked = doc.createElement("locked");
/*     */         
/* 122 */         locked.setTextContent((new StringBuilder(String.valueOf(info.getLocked()))).toString());
/*     */         
/* 124 */         user.appendChild(locked);
/*     */         
/* 126 */         Element faceimg = doc.createElement("faceimg");
/*     */         
/* 128 */         faceimg.setTextContent((new StringBuilder(String.valueOf(info.getFaceimg()))).toString());
/*     */         
/* 130 */         user.appendChild(faceimg);
/*     */         
/* 132 */         root.appendChild(user);
/*     */       } 
/*     */       
/* 135 */       Element pageNow = doc.createElement("page");
/*     */       
/* 137 */       pageNow.setTextContent((new StringBuilder(String.valueOf(page))).toString());
/*     */       
/* 139 */       root.appendChild(pageNow);
/*     */       
/* 141 */       Element maxPageElement = doc.createElement("maxPage");
/*     */       
/* 143 */       maxPageElement.setTextContent((new StringBuilder(String.valueOf(maxPage))).toString());
/*     */       
/* 145 */       root.appendChild(maxPageElement);
/*     */ 
/*     */       
/* 148 */       TransformerFactory.newInstance()
/* 149 */         .newTransformer()
/* 150 */         .transform(new DOMSource(doc), 
/* 151 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     }
/* 153 */     catch (Exception ex) {
/*     */       
/* 155 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetUserByPageServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */