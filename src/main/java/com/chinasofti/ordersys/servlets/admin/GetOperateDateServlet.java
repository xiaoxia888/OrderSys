 package com.chinasofti.ordersys.servlets.admin;

 import com.chinasofti.ordersys.service.waiters.OrderService;
 import com.chinasofti.ordersys.vo.OrderInfo;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
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






 public class GetOperateDateServlet
   extends HttpServlet
 {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
   }











   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/xml");


     try {
       OrderService service = new OrderService();

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

       Date begin = sdf.parse(request.getParameter("bt"));

       Date end = sdf.parse(request.getParameter("et"));

       ArrayList<OrderInfo> list = service.getOrderInfoBetweenDate(begin,
           end);

       Document doc = DocumentBuilderFactory.newInstance()
         .newDocumentBuilder().newDocument();

       Element root = doc.createElement("orders");

       doc.appendChild(root);

       for (OrderInfo info : list) {

         float sumPrice = service.getSumPriceByOrderId(new Integer(info
               .getOrderId()));

         Element order = doc.createElement("order");

         Element orderId = doc.createElement("orderId");

         orderId.setTextContent((new StringBuilder(String.valueOf(info.getOrderId()))).toString());

         order.appendChild(orderId);

         Element tableId = doc.createElement("tableId");

         tableId.setTextContent((new StringBuilder(String.valueOf(info.getTableId()))).toString());

         order.appendChild(tableId);

         Element sumPriceElement = doc.createElement("sumPrice");

         sumPriceElement.setTextContent((new StringBuilder(String.valueOf(sumPrice))).toString());

         order.appendChild(sumPriceElement);

         Element userAccount = doc.createElement("userAccount");

         userAccount.setTextContent(info.getUserAccount());

         order.appendChild(userAccount);

         Element orderEndDate = doc.createElement("orderEndDate");

         sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         orderEndDate.setTextContent(sdf.format(info.getOrderEndDate()));

         order.appendChild(orderEndDate);

         root.appendChild(order);
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

   public void init() throws ServletException {}
 }

