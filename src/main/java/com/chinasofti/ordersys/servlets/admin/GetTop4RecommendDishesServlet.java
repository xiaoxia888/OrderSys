/*     */ package com.chinasofti.ordersys.servlets.admin;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.admin.DishesService;
/*     */ import com.chinasofti.ordersys.vo.DishesInfo;
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
/*     */ public class GetTop4RecommendDishesServlet
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
/*  71 */     DishesService service = new DishesService();
/*     */     
/*  73 */     ArrayList<DishesInfo> list = service.getTop4RecommendDishes();
/*     */ 
/*     */     
/*     */     try {
/*  77 */       Document doc = DocumentBuilderFactory.newInstance()
/*  78 */         .newDocumentBuilder().newDocument();
/*     */       
/*  80 */       Element root = doc.createElement("disheses");
/*     */       
/*  82 */       doc.appendChild(root);
/*     */       
/*  84 */       for (DishesInfo info : list) {
/*     */         
/*  86 */         Element dishes = doc.createElement("dishes");
/*     */         
/*  88 */         Element dishesId = doc.createElement("dishesId");
/*     */         
/*  90 */         dishesId.setTextContent((new StringBuilder(String.valueOf(info.getDishesId()))).toString());
/*     */         
/*  92 */         dishes.appendChild(dishesId);
/*     */         
/*  94 */         Element dishesName = doc.createElement("dishesName");
/*     */         
/*  96 */         dishesName.setTextContent(info.getDishesName());
/*     */         
/*  98 */         dishes.appendChild(dishesName);
/*     */         
/* 100 */         Element dishesDiscript = doc.createElement("dishesDiscript");
/*     */         
/* 102 */         dishesDiscript.setTextContent(info.getDishesDiscript());
/*     */         
/* 104 */         dishes.appendChild(dishesDiscript);
/*     */         
/* 106 */         Element dishesImg = doc.createElement("dishesImg");
/*     */         
/* 108 */         dishesImg.setTextContent(info.getDishesImg());
/*     */         
/* 110 */         dishes.appendChild(dishesImg);
/*     */         
/* 112 */         root.appendChild(dishes);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 117 */       TransformerFactory.newInstance()
/* 118 */         .newTransformer()
/* 119 */         .transform(new DOMSource(doc), 
/* 120 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     
/*     */     }
/* 123 */     catch (Exception ex) {
/*     */       
/* 125 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetTop4RecommendDishesServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */