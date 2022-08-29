package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.vo.DishesInfo;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;














public class GetTop4RecommendDishesServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/xml");

    DishesService service = new DishesService();

    ArrayList<DishesInfo> list = service.getTop4RecommendDishes();


    try {
      Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().newDocument();

      Element root = doc.createElement("disheses");

      doc.appendChild(root);

      for (DishesInfo info : list) {

        Element dishes = doc.createElement("dishes");

        Element dishesId = doc.createElement("dishesId");

        dishesId.setTextContent((new StringBuilder(String.valueOf(info.getDishesId()))).toString());

        dishes.appendChild(dishesId);

        Element dishesName = doc.createElement("dishesName");

        dishesName.setTextContent(info.getDishesName());

        dishes.appendChild(dishesName);

        Element dishesDiscript = doc.createElement("dishesDiscript");

        dishesDiscript.setTextContent(info.getDishesDiscript());

        dishes.appendChild(dishesDiscript);

        Element dishesImg = doc.createElement("dishesImg");

        dishesImg.setTextContent(info.getDishesImg());

        dishes.appendChild(dishesImg);

        root.appendChild(dishes);
      }



      TransformerFactory.newInstance()
        .newTransformer()
        .transform(new DOMSource(doc),
          new StreamResult((OutputStream)response.getOutputStream()));

    }
    catch (Exception ex) {

      ex.printStackTrace();
    }
  }
}


