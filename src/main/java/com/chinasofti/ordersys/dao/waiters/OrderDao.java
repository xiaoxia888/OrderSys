package com.chinasofti.ordersys.dao.waiters;

import com.chinasofti.ordersys.vo.Cart;
import com.chinasofti.ordersys.vo.OrderInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

import java.util.ArrayList;
import java.util.Date;

public class OrderDao {
    public Object addOrder(int waiterId, int tableId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Date now = new Date();

        Object[] key = helper
                .preparedInsertForGeneratedKeys(
                        "insert into orderinfo(orderBeginDate,waiterId,tableId) values(?,?,?)",
                        new Object[] { now, new Integer(waiterId),
                                new Integer(tableId) });

        return key[0];
    }




    public void addOrderDishesMap(Cart.CartUnit unit, Object key) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate(
                "insert into orderdishes(orderReference,dishes,num) values(?,?,?)",
                new Object[] { key, new Integer(unit.getDishesId()),
                        new Integer(unit.getNum()) });
    }


    public ArrayList<OrderInfo> getNeedPayOrdersByPage(int page, int pageSize, int state) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();


        ArrayList<OrderInfo> list = helper
                .preparedForPageList(
                        "select * from orderinfo,userInfo where orderState=? and userInfo.userId=orderinfo.waiterId",
                        new Object[] { new Integer(state) }, page, pageSize,
                        OrderInfo.class);

        return list;
    }



    public int getMaxPage(int pageSize, int state) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Long rows = (Long)helper.preparedQueryForObject(
                "select count(*) from orderinfo where orderState=?",
                new Object[] { new Integer(state) });

        return (int)((rows.longValue() - 1L) / pageSize + 1L);
    }


    public ArrayList<OrderInfo> getNeedPayOrders(int state) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<OrderInfo> list = helper
                .preparedQueryForList(
                        "select * from orderinfo,userInfo where orderState=? and userInfo.userId=orderinfo.waiterId",
                        new Object[] { new Integer(state) }, OrderInfo.class);

        return list;
    }



    public void requestPay(Integer orderId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Date now = new Date();

        helper.executePreparedUpdate(
                "update orderinfo set orderState=1,orderEndDate=? where orderId=?",
                new Object[] { now, new Integer(orderId.intValue()) });
    }


    public OrderInfo getOrderById(Integer orderId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        return helper
                .preparedQueryForList(
                        "select * from orderinfo,userinfo where orderId=? and orderinfo.waiterId=userinfo.userId",
                        new Object[] { orderId }, OrderInfo.class).get(0);
    }



    public float getSumPriceByOrderId(Integer orderId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Double sum = (Double)helper
                .preparedQueryForObject(
                        "SELECT SUM(d.dishesPrice*od.num) FROM orderinfo a,dishesinfo d,orderdishes od WHERE a.orderId=od.orderReference AND od.dishes=d.dishesId AND a.orderId=?",
                        new Object[] { orderId });

        return sum.floatValue();
    }


    public ArrayList<OrderInfo> getOrderDetailById(Integer orderId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        return helper
                .preparedQueryForList(
                        "SELECT * FROM orderinfo o,userinfo u,orderdishes od,dishesinfo d WHERE orderId=? AND o.waiterId=u.userId AND od.orderReference=o.orderId AND d.dishesId=od.dishes",
                        new Object[] { orderId }, OrderInfo.class);
    }



    public void changeState(Integer orderId, int state) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate(
                "update orderinfo set orderState=? where orderId=?",
                new Object[] { new Integer(state), orderId });
    }



    public ArrayList<OrderInfo> getOrderInfoBetweenDate(Date beginDate, Date endDate) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<OrderInfo> list = helper
                .preparedQueryForList(
                        "select * from orderinfo,userInfo where orderState=2 and userInfo.userId=orderinfo.waiterId and orderinfo.orderEndDate between ? and ?",
                        new Object[] { beginDate, endDate }, OrderInfo.class);

        return list;
    }

}
