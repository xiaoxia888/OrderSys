/*     */ package com.chinasofti.ordersys.servlets.waiters;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.admin.DishesService;
/*     */ import com.chinasofti.ordersys.service.waiters.OrderService;
/*     */ import com.chinasofti.ordersys.servlets.kitchen.GetRTOrderServlet;
/*     */ import com.chinasofti.ordersys.vo.Cart;
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import com.chinasofti.util.web.serverpush.MessageProducer;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class CommitCartServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  62 */     doPost(request, response);
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
/*  76 */     response.setCharacterEncoding("utf-8");
/*     */     
/*  78 */     response.setContentType("text/xml");
/*     */     
/*  80 */     DishesService service = new DishesService();
/*     */     
/*  82 */     HttpSession session = request.getSession();
/*     */     
/*  84 */     Cart cart = new Cart();
/*     */     
/*  86 */     Integer tableId = new Integer(1);
/*     */     
/*  88 */     if (session.getAttribute("TABLE_ID") != null)
/*     */     {
/*  90 */       tableId = (Integer)session.getAttribute("TABLE_ID");
/*     */     }
/*     */ 
/*     */     
/*  94 */     if (session.getAttribute("CART") != null)
/*     */     {
/*  96 */       cart = (Cart)session.getAttribute("CART");
/*     */     }
/*     */     
/*  99 */     int waiterId = 1;
/*     */     
/* 101 */     if (session.getAttribute("USER_INFO") != null)
/*     */     {
/* 103 */       waiterId = ((UserInfo)session.getAttribute("USER_INFO"))
/* 104 */         .getUserId();
/*     */     }
/*     */     
/* 107 */     OrderService oservice = new OrderService();
/*     */     
/* 109 */     Object key = oservice.addOrder(waiterId, tableId.intValue());
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 114 */       Document doc = DocumentBuilderFactory.newInstance()
/* 115 */         .newDocumentBuilder().newDocument();
/*     */       
/* 117 */       Element root = doc.createElement("disheses");
/*     */       
/* 119 */       doc.appendChild(root);
/*     */       
/* 121 */       for (Cart.CartUnit unit : cart.getUnits()) {
/*     */         
/* 123 */         oservice.addOrderDishesMap(unit, key);
/*     */         
/* 125 */         Element dishes = doc.createElement("dishes");
/*     */         
/* 127 */         Element tid = doc.createElement("tableId");
/*     */         
/* 129 */         tid.setTextContent((new StringBuilder(String.valueOf(tableId.intValue()))).toString());
/*     */         
/* 131 */         dishes.appendChild(tid);
/*     */         
/* 133 */         Element dishesName = doc.createElement("dishesName");
/*     */         
/* 135 */         String dname = service.getDishesById(
/* 136 */             new Integer(unit.getDishesId())).getDishesName();
/*     */         
/* 138 */         dishesName.setTextContent(dname);
/*     */         
/* 140 */         dishes.appendChild(dishesName);
/*     */         
/* 142 */         Element num = doc.createElement("num");
/*     */         
/* 144 */         num.setTextContent((new StringBuilder(String.valueOf(unit.getNum()))).toString());
/*     */         
/* 146 */         dishes.appendChild(num);
/*     */         
/* 148 */         root.appendChild(dishes);
/*     */       } 
/*     */ 
/*     */       
/* 152 */       StringWriter writer = new StringWriter();
/*     */       
/* 154 */       PrintWriter pwriter = new PrintWriter(writer);
/*     */       
/* 156 */       TransformerFactory.newInstance().newTransformer()
/* 157 */         .transform(new DOMSource(doc), new StreamResult(pwriter));
/*     */       
/* 159 */       String msg = writer.toString();
/*     */       
/* 161 */       pwriter.close();
/*     */       
/* 163 */       writer.close();
/*     */       
/* 165 */       ArrayList<String> list = GetRTOrderServlet.kitchens;
/*     */       
/* 167 */       MessageProducer producer = new MessageProducer();
/*     */       
/* 169 */       for (int i = list.size() - 1; i >= 0; i--) {
/*     */         
/* 171 */         String id = list.get(i);
/*     */         
/* 173 */         producer.sendMessage(id, "rtorder", msg);
/*     */         
/* 175 */         list.remove(id);
/*     */       } 
/*     */       
/* 178 */       cart = new Cart();
/*     */       
/* 180 */       session.setAttribute("CART", cart);
/*     */ 
/*     */     
/*     */     }
/* 184 */     catch (Exception ex) {
/*     */       
/* 186 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\waiters\CommitCartServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */