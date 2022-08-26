/*     */ package com.chinasofti.ordersys.servlets.waiters;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.waiters.OrderService;
/*     */ import com.chinasofti.ordersys.vo.OrderInfo;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.text.SimpleDateFormat;
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
/*     */ public class GetPayListServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  56 */     doPost(request, response);
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
/*  70 */     response.setContentType("text/xml");
/*     */     
/*  72 */     OrderService service = new OrderService();
/*     */     
/*  74 */     int page = 1;
/*     */     
/*  76 */     if (request.getParameter("page") != null)
/*     */     {
/*  78 */       page = Integer.parseInt(request.getParameter("page"));
/*     */     }
/*     */     
/*  81 */     int maxPage = service.getMaxPage(10, 0);
/*     */     
/*  83 */     page = (page < 1) ? 1 : page;
/*     */     
/*  85 */     page = (page > maxPage) ? maxPage : page;
/*     */     
/*  87 */     ArrayList<OrderInfo> list = service.getNeedPayOrdersByPage(page, 10, 0);
/*     */ 
/*     */     
/*     */     try {
/*  91 */       Document doc = DocumentBuilderFactory.newInstance()
/*  92 */         .newDocumentBuilder().newDocument();
/*     */       
/*  94 */       Element root = doc.createElement("orderes");
/*     */       
/*  96 */       doc.appendChild(root);
/*     */       
/*  98 */       for (OrderInfo info : list) {
/*     */         
/* 100 */         Element order = doc.createElement("order");
/*     */         
/* 102 */         Element orderId = doc.createElement("orderId");
/*     */         
/* 104 */         orderId.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());
/*     */         
/* 106 */         order.appendChild(orderId);
/*     */         
/* 108 */         Element tableId = doc.createElement("tableId");
/*     */         
/* 110 */         tableId.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());
/*     */         
/* 112 */         order.appendChild(tableId);
/*     */         
/* 114 */         Element userAccount = doc.createElement("userAccount");
/*     */         
/* 116 */         userAccount.setTextContent(info.getUserAccount());
/*     */         
/* 118 */         order.appendChild(userAccount);
/*     */         
/* 120 */         Element orderBeginDate = doc.createElement("orderBeginDate");
/*     */         
/* 122 */         SimpleDateFormat sdf = new SimpleDateFormat(
/* 123 */             "yyyy-MM-dd HH:mm:ss");
/*     */         
/* 125 */         orderBeginDate.setTextContent(sdf.format(info
/* 126 */               .getOrderBeginDate()));
/*     */         
/* 128 */         order.appendChild(orderBeginDate);
/*     */         
/* 130 */         root.appendChild(order);
/*     */       } 
/*     */ 
/*     */       
/* 134 */       Element pageNow = doc.createElement("page");
/*     */       
/* 136 */       pageNow.setTextContent((new StringBuilder(String.valueOf(page))).toString());
/*     */       
/* 138 */       root.appendChild(pageNow);
/*     */       
/* 140 */       Element maxPageElement = doc.createElement("maxPage");
/*     */       
/* 142 */       maxPageElement.setTextContent((new StringBuilder(String.valueOf(maxPage))).toString());
/*     */       
/* 144 */       root.appendChild(maxPageElement);
/*     */ 
/*     */       
/* 147 */       TransformerFactory.newInstance()
/* 148 */         .newTransformer()
/* 149 */         .transform(new DOMSource(doc), 
/* 150 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     }
/* 152 */     catch (Exception ex) {
/*     */       
/* 154 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\waiters\GetPayListServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */