package com.chinasofti.ordersys.servlets.kitchen;

 import com.chinasofti.util.web.serverpush.MessageProducer;
 import java.io.IOException;
 import java.util.ArrayList;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;









 public class DishesDoneServlet extends HttpServlet {
       public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
       }






       public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setCharacterEncoding("utf-8");


            String tableId = request.getParameter("tableId");

            String dishesName = request.getParameter("dishesName");
            //System.out.println("dishesName"+dishesName);

            //dishesName = new String(dishesName.getBytes("iso8859-1"), "utf-8");
            //System.out.println("dishesName2"+dishesName);
            MessageProducer producer = new MessageProducer();

            ArrayList<String> list = GetRTDishesServlet.disheses;

            for (int i = list.size() - 1; i >= 0; i--) {

                String id = list.get(i);

            producer.sendMessage(id, "rtdishes", "桌号[" + tableId + "]的菜品[" +
                dishesName + "]已经烹制完成，请传菜！");

            list.remove(id);
            }
      }
}


