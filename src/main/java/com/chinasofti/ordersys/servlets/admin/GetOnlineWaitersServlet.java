/*     */ package com.chinasofti.ordersys.servlets.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.listeners.OrderSysListener;
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
/*     */ public class GetOnlineWaitersServlet
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
/*     */   
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  70 */     response.setContentType("text/xml");
/*     */     
/*  72 */     ArrayList<UserInfo> waiters = OrderSysListener.getOnlineWaiters();
/*     */     
/*  74 */     int sessions = OrderSysListener.onlineSessions;
/*     */ 
/*     */     
/*     */     try {
/*  78 */       Document doc = DocumentBuilderFactory.newInstance()
/*  79 */         .newDocumentBuilder().newDocument();
/*     */       
/*  81 */       Element root = doc.createElement("users");
/*     */       
/*  83 */       doc.appendChild(root);
/*     */       
/*  85 */       for (UserInfo info : waiters) {
/*     */         
/*  87 */         Element user = doc.createElement("user");
/*     */         
/*  89 */         Element userId = doc.createElement("userId");
/*     */         
/*  91 */         userId.setTextContent((new StringBuilder(String.valueOf(info.getUserId()))).toString());
/*     */         
/*  93 */         user.appendChild(userId);
/*     */         
/*  95 */         Element userAccount = doc.createElement("userAccount");
/*     */         
/*  97 */         userAccount.setTextContent(info.getUserAccount());
/*     */         
/*  99 */         user.appendChild(userAccount);
/*     */         
/* 101 */         Element roleId = doc.createElement("roleId");
/*     */         
/* 103 */         roleId.setTextContent((new StringBuilder(String.valueOf(info.getRoleId()))).toString());
/*     */         
/* 105 */         user.appendChild(roleId);
/*     */         
/* 107 */         Element roleName = doc.createElement("roleName");
/*     */         
/* 109 */         roleName.setTextContent(info.getRoleName());
/*     */         
/* 111 */         user.appendChild(roleName);
/*     */         
/* 113 */         Element locked = doc.createElement("locked");
/*     */         
/* 115 */         locked.setTextContent((new StringBuilder(String.valueOf(info.getLocked()))).toString());
/*     */         
/* 117 */         user.appendChild(locked);
/*     */         
/* 119 */         Element faceimg = doc.createElement("faceimg");
/*     */         
/* 121 */         faceimg.setTextContent((new StringBuilder(String.valueOf(info.getFaceimg()))).toString());
/*     */         
/* 123 */         user.appendChild(faceimg);
/*     */         
/* 125 */         root.appendChild(user);
/*     */       } 
/*     */ 
/*     */       
/* 129 */       Element sessionNum = doc.createElement("sessionNum");
/*     */       
/* 131 */       sessionNum.setTextContent((new StringBuilder(String.valueOf(sessions))).toString());
/*     */       
/* 133 */       root.appendChild(sessionNum);
/*     */       
/* 135 */       Element waitersNum = doc.createElement("waitersNum");
/*     */       
/* 137 */       waitersNum.setTextContent((new StringBuilder(String.valueOf(waiters.size()))).toString());
/*     */       
/* 139 */       root.appendChild(waitersNum);
/*     */ 
/*     */       
/* 142 */       TransformerFactory.newInstance()
/* 143 */         .newTransformer()
/* 144 */         .transform(new DOMSource(doc), 
/* 145 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     }
/* 147 */     catch (Exception ex) {
/*     */       
/* 149 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetOnlineWaitersServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */