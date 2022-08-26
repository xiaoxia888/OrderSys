/*    */ package com.chinasofti.ordersys.servlets.admin;
/*    */ 
/*    */ import com.chinasofti.ordersys.service.admin.DishesService;
/*    */ import com.chinasofti.ordersys.vo.DishesInfo;
/*    */ import com.chinasofti.util.web.upload.MultipartRequestParser;
/*    */ import com.chinasofti.web.common.taglib.TokenTag;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
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
/*    */ public class ModifyDishesServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 49 */     doPost(request, response);
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
/* 63 */    /* if (TokenTag.isTokenValid()) {*/

/*    */       
/* 65 */       DishesService service = new DishesService();
/*    */       
/* 67 */       MultipartRequestParser parser = new MultipartRequestParser();
/*    */       
/* 69 */       DishesInfo info = (DishesInfo)parser.parse(request, 
/* 70 */           DishesInfo.class);
/*    */       
/* 72 */       service.modifyDishes(info);
/*    */       
/* 74 */       TokenTag.releaseToken();
/*    */     /*} */
/*    */     
/* 77 */     response.sendRedirect("/OrderSys/todishesadmin.order");
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\admin\ModifyDishesServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */