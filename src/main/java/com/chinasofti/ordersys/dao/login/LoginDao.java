package com.chinasofti.ordersys.dao.login;

import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

import java.util.ArrayList;
import java.util.List;

public class LoginDao {

    public List<UserInfo> getUserInfo(UserInfo info) {
        JDBCTemplateWithDS dbHelper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<UserInfo> userList = dbHelper
                .preparedQueryForList(
                        "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId and userinfo.userAccount=?",
                        new Object[] { info.getUserAccount() }, UserInfo.class);
        return userList;
    }
}
