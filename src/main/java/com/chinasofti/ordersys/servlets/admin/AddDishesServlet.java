 package com.chinasofti.ordersys.servlets.admin;

 import com.chinasofti.ordersys.service.admin.DishesService;
 import com.chinasofti.ordersys.vo.DishesInfo;
 import com.chinasofti.util.web.upload.MultipartRequestParser;
 import com.chinasofti.web.common.taglib.TokenTag;
 import java.io.IOException;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

































 public class AddDishesServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }











   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     if (TokenTag.isTokenValid()) {

       MultipartRequestParser parser = new MultipartRequestParser();

       DishesInfo info = (DishesInfo)parser.parse(request,
           DishesInfo.class);

       DishesService service = new DishesService();

       service.addDishes(info);

       TokenTag.releaseToken();
     }

     response.sendRedirect("/OrderSys/todishesadmin.order");
   }
 }

