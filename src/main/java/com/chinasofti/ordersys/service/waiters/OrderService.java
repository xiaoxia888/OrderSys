/*     */ package com.chinasofti.ordersys.service.waiters;

     import com.chinasofti.ordersys.dao.waiters.OrderDao;
     import com.chinasofti.ordersys.vo.Cart;
     import com.chinasofti.ordersys.vo.OrderInfo;
     import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
     import java.util.ArrayList;
     import java.util.Date;





public class OrderService {
    private OrderDao orderDao = new OrderDao();
       public Object addOrder(int waiterId, int tableId) {
           Object o = orderDao.addOrder(waiterId, tableId);

           return o;
       }





       public void addOrderDishesMap(Cart.CartUnit unit, Object key) {

         orderDao.addOrderDishesMap(unit,key);

       }







       public ArrayList<OrderInfo> getNeedPayOrdersByPage(int page, int pageSize, int state) {
           ArrayList<OrderInfo> needPayOrdersByPage = orderDao.getNeedPayOrdersByPage(page, pageSize, state);

           return needPayOrdersByPage;
       }







       public int getMaxPage(int pageSize, int state) {
           int maxPage = orderDao.getMaxPage(pageSize, state);

           return maxPage;
       }







       public ArrayList<OrderInfo> getNeedPayOrders(int state) {

         return orderDao.getNeedPayOrders(state);

       }





       public void requestPay(Integer orderId) {

         orderDao.requestPay(orderId);

       }







       public OrderInfo getOrderById(Integer orderId) {
           OrderInfo orderById = orderDao.getOrderById(orderId);

           return orderById;
       }






       public float getSumPriceByOrderId(Integer orderId) {


           return orderDao.getSumPriceByOrderId(orderId);
       }







       public ArrayList<OrderInfo> getOrderDetailById(Integer orderId) {

         return orderDao.getOrderDetailById(orderId);

       }








       public void changeState(Integer orderId, int state) {

         orderDao.changeState(orderId,state);

       }









       public ArrayList<OrderInfo> getOrderInfoBetweenDate(Date beginDate, Date endDate) {
           ArrayList<OrderInfo> orderInfoBetweenDate = orderDao.getOrderInfoBetweenDate(beginDate, endDate);

           return orderInfoBetweenDate;
       }
     }

