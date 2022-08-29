package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.vo.DishesInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


































public class ToModifyDishesServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DishesService service = new DishesService();

    DishesInfo info = service.getDishesById(new Integer(request
          .getParameter("dishesId")));

    request.setAttribute("DISHES_INFO", info);

    request.getRequestDispatcher("/pages/admin/modifydishes.jsp").forward(
        (ServletRequest)request, (ServletResponse)response);
  }
}

