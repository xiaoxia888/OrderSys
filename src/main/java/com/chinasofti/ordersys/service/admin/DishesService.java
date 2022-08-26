package com.chinasofti.ordersys.service.admin;

import com.chinasofti.ordersys.dao.admin.DishesDao;
import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.util.jdbc.ConnectionFactory;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

import java.sql.Connection;
import java.util.ArrayList;











public class DishesService
{
    private DishesDao dishesDao = new DishesDao();
    public ArrayList<DishesInfo> getDishesInfoByPage(int page, int pageSize) {
      return dishesDao.getDishesInfoByPage(page,pageSize);
    }










  public int getMaxPage(int pageSize) {
    return dishesDao.getMaxPage(pageSize);
  }








  public void deleteDishesById(Integer dishesId) {
    dishesDao.deleteDishesById(dishesId);
  }








  public void addDishes(DishesInfo info) {
    dishesDao.addDishes(info);
  }










  public DishesInfo getDishesById(Integer dishesId) {
    DishesInfo dishesById = dishesDao.getDishesById(dishesId);
    return dishesById;
  }








  public void modifyDishes(DishesInfo info) {
   dishesDao.modifyDishes(info);
  }








  public ArrayList<DishesInfo> getTop4RecommendDishes() {
    ArrayList<DishesInfo> top4RecommendDishes = dishesDao.getTop4RecommendDishes();


    return top4RecommendDishes;
  }
}

