package com.chinasofti.ordersys.service.login;

import com.chinasofti.ordersys.dao.login.LoginDao;
import com.chinasofti.ordersys.listeners.OrderSysListener;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

import java.util.*;


public class LoginService {

    public static final int WRONG_USERNAME = 0;
    public static final int WRONG_PASSWORD = 1;
    public static final int USER_ALREADY_ONLINE = 2;
    public static final int WRONG_LOCKED = 3;
    public static final int WRONG_OTHER = 4;
    public static final int LOGIN_OK = 5;
    private UserInfo loginUser = null;

    private LoginDao loginDao = new LoginDao();



    public UserInfo getLoginUser() {
      return this.loginUser;
    }




    public int login(UserInfo info) {
      UserInfo dbUser;
      Hashtable<String, UserInfo> loginUserMap = OrderSysListener.sessions;

      Set<String> loginIds = loginUserMap.keySet();

      Iterator<String> it = loginIds.iterator();

      while (it.hasNext()) {

        UserInfo user = loginUserMap.get(it.next());

        if (user.getUserAccount().equals(info.getUserAccount()))
        {
          return 2;
        }
      }

      List<UserInfo> userList = loginDao.getUserInfo(info);

      switch (userList.size()) {


        case 0:
          return 0;


        case 1:
          dbUser = userList.get(0);



          if (dbUser.getLocked() == 1) {

            this.loginUser = dbUser;

            return 3;
          }

          if (info.getUserPass().equals(dbUser.getUserPass())) {

            this.loginUser = dbUser;

            return 5;
          }


          return 1;
      }



      return 4;
    }
}

