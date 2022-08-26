/*     */ package com.chinasofti.ordersys.servlets.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.waiters.OrderService;
/*     */ import com.chinasofti.ordersys.vo.OrderInfo;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class GetOperateDateServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  57 */     doPost(request, response);
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
/*  71 */     response.setContentType("text/xml");
/*     */ 
/*     */     
/*     */     try {
/*  75 */       OrderService service = new OrderService();
/*     */       
/*  77 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/*     */       
/*  79 */       Date begin = sdf.parse(request.getParameter("bt"));
/*     */       
/*  81 */       Date end = sdf.parse(request.getParameter("et"));
/*     */       
/*  83 */       ArrayList<OrderInfo> list = service.getOrderInfoBetweenDate(begin, 
/*  84 */           end);
/*     */       
/*  86 */       Document doc = DocumentBuilderFactory.newInstance()
/*  87 */         .newDocumentBuilder().newDocument();
/*     */       
/*  89 */       Element root = doc.createElement("orders");
/*     */       
/*  91 */       doc.appendChild(root);
/*     */       
/*  93 */       for (OrderInfo info : list) {
/*     */         
/*  95 */         float sumPrice = service.getSumPriceByOrderId(new Integer(info
/*  96 */               .getOrderId()));
/*     */         
/*  98 */         Element order = doc.createElement("order");
/*     */         
/* 100 */         Element orderId = doc.createElement("orderId");
/*     */         
/* 102 */         orderId.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());
/*     */         
/* 104 */         order.appendChild(orderId);
/*     */         
/* 106 */         Element tableId = doc.createElement("tableId");
/*     */         
/* 108 */         tableId.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());
/*     */         
/* 110 */         order.appendChild(tableId);
/*     */         
/* 112 */         Element sumPriceElement = doc.createElement("sumPrice");
/*     */         
/* 114 */         sumPriceElement.setTextContent((new StringBuilder(String.valueOf(sumPrice))).toString());
/*     */         
/* 116 */         order.appendChild(sumPriceElement);
/*     */         
/* 118 */         Element userAccount = doc.createElement("userAccount");
/*     */         
/* 120 */         userAccount.setTextContent(info.getUserAccount());
/*     */         
/* 122 */         order.appendChild(userAccount);
/*     */         
/* 124 */         Element orderEndDate = doc.createElement("orderEndDate");
/*     */         
/* 126 */         sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */         
/* 128 */         orderEndDate.setTextContent(sdf.format(info.getOrderEndDate()));
/*     */         
/* 130 */         order.appendChild(orderEndDate);
/*     */         
/* 132 */         root.appendChild(order);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 137 */       TransformerFactory.newInstance()
/* 138 */         .newTransformer()
/* 139 */         .transform(new DOMSource(doc), 
/* 140 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     
/*     */     }
/* 143 */     catch (Exception ex) {
/*     */       
/* 145 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init() throws ServletException {}
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetOperateDateServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */