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
/*     */ public class GetDishesInfoByPageServlet
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
/*  73 */     DishesService service = new DishesService();
/*     */     
/*  75 */     int maxPage = service.getMaxPage(8);
/*     */     
/*  77 */     page = (page < 1) ? 1 : page;
/*     */     
/*  79 */     page = (page > maxPage) ? maxPage : page;
/*     */     
/*  81 */     ArrayList<DishesInfo> list = service.getDishesInfoByPage(page, 8);
/*     */ 
/*     */     
/*     */     try {
/*  85 */       Document doc = DocumentBuilderFactory.newInstance()
/*  86 */         .newDocumentBuilder().newDocument();
/*     */       
/*  88 */       Element root = doc.createElement("disheses");
/*     */       
/*  90 */       doc.appendChild(root);
/*     */       
/*  92 */       for (DishesInfo info : list) {
/*     */         
/*  94 */         Element dishes = doc.createElement("dishes");
/*     */         
/*  96 */         Element dishesId = doc.createElement("dishesId");
/*     */         
/*  98 */         dishesId.setTextContent((new StringBuilder(String.valueOf(info.getDishesId()))).toString());
/*     */         
/* 100 */         dishes.appendChild(dishesId);
/*     */         
/* 102 */         Element dishesName = doc.createElement("dishesName");
/*     */         
/* 104 */         dishesName.setTextContent(info.getDishesName());
/*     */         
/* 106 */         dishes.appendChild(dishesName);
/*     */         
/* 108 */         Element dishesDiscript = doc.createElement("dishesDiscript");
/*     */         
/* 110 */         dishesDiscript.setTextContent(info.getDishesDiscript());
/*     */         
/* 112 */         dishes.appendChild(dishesDiscript);
/*     */         
/* 114 */         Element dishesImg = doc.createElement("dishesImg");
/*     */         
/* 116 */         dishesImg.setTextContent(info.getDishesImg());
/*     */         
/* 118 */         dishes.appendChild(dishesImg);
/*     */         
/* 120 */         Element dishesTxt = doc.createElement("dishesTxt");
/*     */         
/* 122 */         String txt = info.getDishesTxt();
/*     */         
/* 124 */         txt = txt.replaceAll(" ", "ordersysspace");
/*     */         
/* 126 */         txt = txt.replaceAll("\r", "");
/*     */         
/* 128 */         txt = txt.replaceAll("\n", "ordersysbreak");
/*     */         
/* 130 */         txt = txt.replaceAll("\"", "\\\\\"");
/*     */         
/* 132 */         txt = txt.replaceAll("'", "\\\\'");
/*     */         
/* 134 */         dishesTxt.setTextContent(txt);
/*     */         
/* 136 */         dishes.appendChild(dishesTxt);
/*     */         
/* 138 */         Element recommend = doc.createElement("recommend");
/*     */         
/* 140 */         recommend.setTextContent((new StringBuilder(String.valueOf(info.getRecommend()))).toString());
/*     */         
/* 142 */         dishes.appendChild(recommend);
/*     */         
/* 144 */         Element dishesPrice = doc.createElement("dishesPrice");
/*     */         
/* 146 */         dishesPrice.setTextContent((new StringBuilder(String.valueOf(info.getDishesPrice()))).toString());
/*     */         
/* 148 */         dishes.appendChild(dishesPrice);
/*     */         
/* 150 */         root.appendChild(dishes);
/*     */       } 
/*     */ 
/*     */       
/* 154 */       Element pageNow = doc.createElement("page");
/*     */       
/* 156 */       pageNow.setTextContent((new StringBuilder(String.valueOf(page))).toString());
/*     */       
/* 158 */       root.appendChild(pageNow);
/*     */       
/* 160 */       Element maxPageElement = doc.createElement("maxPage");
/*     */       
/* 162 */       maxPageElement.setTextContent((new StringBuilder(String.valueOf(maxPage))).toString());
/*     */       
/* 164 */       root.appendChild(maxPageElement);
/*     */ 
/*     */       
/* 167 */       TransformerFactory.newInstance()
/* 168 */         .newTransformer()
/* 169 */         .transform(new DOMSource(doc), 
/* 170 */           new StreamResult((OutputStream)response.getOutputStream()));
/*     */     
/*     */     }
/* 173 */     catch (Exception ex) {
/*     */       
/* 175 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\GetDishesInfoByPageServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */