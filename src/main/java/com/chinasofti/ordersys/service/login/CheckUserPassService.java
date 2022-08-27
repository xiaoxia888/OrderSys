package com.chinasofti.ordersys.service.login;

import com.chinasofti.ordersys.dao.login.CheckUserPassDao;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
import com.chinasofti.util.sec.Passport;
import java.util.ArrayList;
import java.util.List;


public class CheckUserPassService {
    private CheckUserPassDao checkUserPassDao = new CheckUserPassDao();
  public boolean checkPass(UserInfo info) {
    Passport passport;
    JDBCTemplateWithDS dbHelper = JDBCTemplateWithDS.getJDBCHelper();

      List<UserInfo> userList = checkUserPassDao.getUserInfo(info);

    switch (userList.size()) {


      case 0:
        return false;


      case 1:
        passport = new Passport();

        if (((UserInfo)userList.get(0)).getUserPass()
          .equals(passport.md5(info.getUserPass())))
        {
          return true;
        }


        return false;
    }

    return false;
  }
}

