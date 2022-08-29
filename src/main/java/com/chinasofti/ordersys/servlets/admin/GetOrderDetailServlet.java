 package com.chinasofti.ordersys.servlets.admin;

 import com.chinasofti.ordersys.service.waiters.OrderService;
 import com.chinasofti.ordersys.vo.OrderInfo;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.SimpleDateFormat;
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












 public class GetOrderDetailServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }











   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/xml");

     Integer orderId = new Integer(request.getParameter("orderId"));

     OrderService service = new OrderService();

     ArrayList<OrderInfo> list = service.getOrderDetailById(orderId);

     OrderInfo info = service.getOrderById(orderId);


     try {
       Document doc = DocumentBuilderFactory.newInstance()
         .newDocumentBuilder().newDocument();

       Element root = doc.createElement("order");

       doc.appendChild(root);

       Element oidElement = doc.createElement("orderId");

       oidElement.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());

       root.appendChild(oidElement);

       Element userAccountElement = doc.createElement("userAccount");

       userAccountElement.setTextContent(info.getUserAccount());

       root.appendChild(userAccountElement);

       Element tid = doc.createElement("tableId");

       tid.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());

       root.appendChild(tid);

       Element orderBeginDateElement = doc.createElement("orderBeginDate");

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       orderBeginDateElement.setTextContent(sdf.format(info
             .getOrderBeginDate()));

       root.appendChild(orderBeginDateElement);

       Element orderEndDateElement = doc.createElement("orderEndDate");

       orderEndDateElement.setTextContent(sdf.format(info
             .getOrderEndDate()));

       root.appendChild(orderEndDateElement);

       double sum = service.getSumPriceByOrderId(orderId);

       Element sumPrice = doc.createElement("sumPrice");

       sumPrice.setTextContent((new StringBuilder(String.valueOf(sum))).toString());

       root.appendChild(sumPrice);

       for (OrderInfo oi : list) {

         Element unit = doc.createElement("unit");

         root.appendChild(unit);

         Element dishesName = doc.createElement("dishesName");

         dishesName.setTextContent(oi.getDishesName());

         unit.appendChild(dishesName);

         Element dishesPrice = doc.createElement("dishesPrice");

         dishesPrice.setTextContent((new StringBuilder(String.valueOf(oi.getDishesPrice()))).toString());

         unit.appendChild(dishesPrice);

         Element num = doc.createElement("num");

         num.setTextContent((new StringBuilder(String.valueOf(oi.getNum()))).toString());

         unit.appendChild(num);
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


