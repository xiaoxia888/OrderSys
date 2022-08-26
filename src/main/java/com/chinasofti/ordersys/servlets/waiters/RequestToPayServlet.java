/*     */ package com.chinasofti.ordersys.servlets.waiters;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.waiters.OrderService;
/*     */ import com.chinasofti.ordersys.servlets.admin.GetRTPayOrderServlet;
/*     */ import com.chinasofti.ordersys.vo.OrderInfo;
/*     */ import com.chinasofti.util.web.serverpush.MessageProducer;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
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
/*     */ 
/*     */ public class RequestToPayServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  60 */     doPost(request, response);
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
/*  74 */     Integer orderId = new Integer(request.getParameter("orderId"));
/*     */     
/*  76 */     OrderService service = new OrderService();
/*     */     
/*  78 */     service.requestPay(orderId);
/*     */     
/*  80 */     OrderInfo info = service.getOrderById(orderId);
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Document doc = DocumentBuilderFactory.newInstance()
/*  85 */         .newDocumentBuilder().newDocument();
/*     */       
/*  87 */       Element root = doc.createElement("order");
/*     */       
/*  89 */       doc.appendChild(root);
/*     */       
/*  91 */       Element oid = doc.createElement("orderId");
/*     */       
/*  93 */       oid.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());
/*     */       
/*  95 */       root.appendChild(oid);
/*     */       
/*  97 */       Element userAccount = doc.createElement("userAccount");
/*     */       
/*  99 */       userAccount.setTextContent(info.getUserAccount());
/*     */       
/* 101 */       root.appendChild(userAccount);
/*     */       
/* 103 */       Element tid = doc.createElement("tableId");
/*     */       
/* 105 */       tid.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());
/*     */       
/* 107 */       root.appendChild(tid);
/*     */       
/* 109 */       Element orderBeginDate = doc.createElement("orderBeginDate");
/*     */       
/* 111 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */       
/* 113 */       orderBeginDate.setTextContent(sdf.format(info.getOrderBeginDate()));
/*     */       
/* 115 */       root.appendChild(orderBeginDate);
/*     */       
/* 117 */       Element orderEndDate = doc.createElement("orderEndDate");
/*     */       
/* 119 */       orderEndDate.setTextContent(sdf.format(info.getOrderEndDate()));
/*     */       
/* 121 */       root.appendChild(orderEndDate);
/*     */       
/* 123 */       double sum = service.getSumPriceByOrderId(orderId);
/*     */       
/* 125 */       Element sumPrice = doc.createElement("sumPrice");
/*     */       
/* 127 */       sumPrice.setTextContent((new StringBuilder(String.valueOf(sum))).toString());
/*     */       
/* 129 */       root.appendChild(sumPrice);
/*     */       
/* 131 */       StringWriter writer = new StringWriter();
/*     */       
/* 133 */       PrintWriter pwriter = new PrintWriter(writer);
/*     */       
/* 135 */       TransformerFactory.newInstance().newTransformer()
/* 136 */         .transform(new DOMSource(doc), new StreamResult(pwriter));
/*     */       
/* 138 */       String msg = writer.toString();
/*     */       
/* 140 */       pwriter.close();
/*     */       
/* 142 */       writer.close();
/*     */       
/* 144 */       ArrayList<String> list = GetRTPayOrderServlet.pays;
/*     */       
/* 146 */       MessageProducer producer = new MessageProducer();
/*     */       
/* 148 */       for (int i = list.size() - 1; i >= 0; i--)
/*     */       {
/* 150 */         String id = list.get(i);
/*     */         
/* 152 */         producer.sendMessage(id, "rtpay", msg);
/*     */         
/* 154 */         list.remove(id);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 159 */     catch (Exception ex) {
/*     */       
/* 161 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\waiters\RequestToPayServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */