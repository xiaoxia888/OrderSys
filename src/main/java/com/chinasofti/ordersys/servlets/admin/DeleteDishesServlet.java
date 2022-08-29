package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.DishesService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

































public class DeleteDishesServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer dishesId = new Integer(request.getParameter("dishesId"));



    DishesService service = new DishesService();

    service.deleteDishesById(dishesId);
  }
}

