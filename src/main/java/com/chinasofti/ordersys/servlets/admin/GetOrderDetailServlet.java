/*     */ package com.chinasofti.ordersys.servlets.admin;
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
/*     */ public class GetOrderDetailServlet
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
/*  72 */     Integer orderId = new Integer(request.getParameter("orderId"));
/*     */     
/*  74 */     OrderService service = new OrderService();
/*     */     
/*  76 */     ArrayList<OrderInfo> list = service.getOrderDetailById(orderId);
/*     */     
/*  78 */     OrderInfo info = service.getOrderById(orderId);
/*     */ 
/*     */     
/*     */     try {
/*  82 */       Document doc = DocumentBuilderFactory.newInstance()
/*  83 */         .newDocumentBuilder().newDocument();
/*     */       
/*  85 */       Element root = doc.createElement("order");
/*     */       
/*  87 */       doc.appendChild(root);
/*     */       
/*  89 */       Element oidElement = doc.createElement("orderId");
/*     */       
/*  91 */       oidElement.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());
/*     */       
/*  93 */       root.appendChild(oidElement);
/*     */       
/*  95 */       Element userAccountElement = doc.createElement("userAccount");
/*     */       
/*  97 */       userAccountElement.setTextContent(info.getUserAccount());
/*     */       
/*  99 */       root.appendChild(userAccountElement);
/*     */       
/* 101 */       Element tid = doc.createElement("tableId");
/*     */       
/* 103 */       tid.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());
/*     */       
/* 105 */       root.appendChild(tid);
/*     */       
/* 107 */       Element orderBeginDateElement = doc.createElement("orderBeginDate");
/*     */       
/* 109 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */       
/* 111 */       orderBeginDateElement.setTextContent(sdf.format(info
/* 112 */             .getOrderBeginDate()));
/*     */       
/* 114 */       root.appendChild(orderBeginDateElement);
/*     */       
/* 116 */       Element orderEndDateElement = doc.createElement("orderEndDate");
/*     */       
/* 118 */       orderEndDateElement.setTextContent(sdf.format(info
/* 119 */             .getOrderEndDate()));
/*     */       
/* 121 */       root.appendChild(orderEndDateElement);
/*     */       
/* 123 */       double sum = service.getSumPriceByOrderId(orderId);
/*     */       
/* 125 */       Element sumPrice = doc.createElement("sumPrice");
/*     */       
/* 127 */       sumPrice.setTextContent((new StringBuilder(String.valueOf(sum))).toString());
/*     */       
/* 129 */       root.appendChild(sumPrice);
/*     */       
/* 131 */       for (OrderInfo oi : list) {
/*     */         
/* 133 */         Element unit = doc.createElement("unit");
/*     */         
/* 135 */         root.appendChild(unit);
/*     */         
/* 137 */         Element dishesName = doc.createElement("dishesName");
/*     */         
/* 139 */         dishesName.setTextContent(oi.getDishesName());
/*     */         
/* 141 */         unit.appendChild(dishesName);
/*     */         
/* 143 */         Element dishesPrice = doc.createElement("dishesPrice");
/*     */         
/* 145 */         dishesPrice.setTextContent((new StringBuilder(String.valueOf(oi.getDishesPrice()))).toString());
/*     */         
/* 147 */         unit.appendChild(dishesPrice);
/*     */         
/* 149 */         Element num = doc.createElement("num");
/*     */         
/* 151 */         num.setTextContent((new StringBuilder(String.valueOf(oi.getNum()))).toString());
/*     */         
/* 153 */         unit.appendChild(num);
/*     */       } 
/*     */ 
/*     */       
/* 157 */       TransformerFactory.newInstance()
/* 158 */         .newTransformer()
/* 159 */         .transform(new DOMSource(doc), 
/* 160 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */ 
/*     */     
/*     */     }
/* 164 */     catch (Exception ex) {
/*     */       
/* 166 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetOrderDetailServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */