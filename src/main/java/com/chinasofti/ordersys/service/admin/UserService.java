 package com.chinasofti.ordersys.service.admin;

import com.chinasofti.ordersys.dao.admin.UserDao;
import com.chinasofti.ordersys.vo.UserInfo;
 import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
 import com.chinasofti.util.sec.Passport;
 import java.util.ArrayList;

public class UserService {
  private UserDao userDao= new UserDao();
  public ArrayList<UserInfo> getByPage(int page, int pageSize) {
    ArrayList<UserInfo> byPage = userDao.getByPage(page, pageSize);
    return byPage;
  }





  public int getMaxPage(int pageSize) {
    int maxPage = userDao.getMaxPage(pageSize);

    return maxPage;
  }





  public void addUser(UserInfo info) {

    userDao.addUser(info);
  }




  public void deleteUser(Integer userId) {
    userDao.deleteUser(userId);
  }




  public void modify(UserInfo info) {
    userDao.modify(info);
  }





  public void adminModify(UserInfo info) {
    userDao.adminModify(info);
  }






  public UserInfo getUserById(Integer userId) {
    UserInfo userById = userDao.getUserById(userId);

    return userById;
  }
}
