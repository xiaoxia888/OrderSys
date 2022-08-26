package com.chinasofti.ordersys.dao.admin;

import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

import java.util.ArrayList;

public class DishesDao {
    public ArrayList<DishesInfo> getDishesInfoByPage(int page, int pageSize) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<DishesInfo> list = helper.preparedForPageList(
                "select * from dishesinfo order by recommend desc,dishesId",
                new Object[0], page, pageSize, DishesInfo.class);

        return list;
    }

    public int getMaxPage(int pageSize) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Long rows = (Long)helper.preparedQueryForObject(
                "select count(*) from dishesinfo", new Object[0]);

        return (int)((rows.longValue() - 1L) / pageSize + 1L);
    }

    public void deleteDishesById(Integer dishesId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate("delete from dishesinfo where dishesId=?",
                new Object[] { dishesId });
    }

    public void addDishes(DishesInfo info) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate(
                "insert into dishesinfo(dishesName,dishesDiscript,dishesTxt,dishesImg,recommend,dishesPrice) values(?,?,?,?,?,?)",
                new Object[] { info.getDishesName(), info.getDishesDiscript(),
                        info.getDishesTxt(), info.getDishesImg(),
                        new Integer(info.getRecommend()),
                        new Float(info.getDishesPrice()) });
    }

    public DishesInfo getDishesById(Integer dishesId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<DishesInfo> list = helper.preparedQueryForList(
                "select * from dishesinfo where dishesId=?",
                new Object[] { dishesId }, DishesInfo.class);

        return list.get(0);
    }


    public void modifyDishes(DishesInfo info) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate(
                "update dishesinfo set dishesName=?,dishesDiscript=?,dishesTxt=?,dishesImg=?,recommend=?,dishesPrice=? where dishesId=?",
                new Object[] { info.getDishesName(), info.getDishesDiscript(),
                        info.getDishesTxt(), info.getDishesImg(),
                        new Integer(info.getRecommend()),
                        new Float(info.getDishesPrice()),
                        new Integer(info.getDishesId()) });
    }



    public ArrayList<DishesInfo> getTop4RecommendDishes() {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<DishesInfo> list = helper.preparedForPageList(
                "select * from dishesinfo where recommend=1 order by dishesId",
                new Object[0], 1, 4, DishesInfo.class);
        return list;
    }

}
