/*    */ package com.chinasofti.ordersys.servlets.waiters;
/*    */ 
/*    */ import com.chinasofti.ordersys.vo.Cart;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
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
/*    */ public class AddCartServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 47 */     doPost(request, response);
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
/* 61 */     HttpSession session = request.getSession();
/*    */     
/* 63 */     Cart cart = new Cart();
/*    */     
/* 65 */     if (session.getAttribute("CART") != null)
/*    */     {
/* 67 */       cart = (Cart)session.getAttribute("CART");
/*    */     }
/*    */     
/* 70 */     Integer tableId = Integer.valueOf(1);
/*    */     
/* 72 */     if (session.getAttribute("TABLE_ID") != null)
/*    */     {
/* 74 */       tableId = (Integer)session.getAttribute("TABLE_ID");
/*    */     }
/*    */     
/* 77 */     cart.setTableId(tableId.intValue());
/*    */     
/* 79 */     int num = Integer.parseInt(request.getParameter("num"));
/*    */     
/* 81 */     int dishesId = Integer.parseInt(request.getParameter("dishes"));
/*    */     
/* 83 */     cart.getUnits().add(cart.createUnit(dishesId, num));
/*    */     
/* 85 */     session.setAttribute("CART", cart);
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\waiters\AddCartServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */