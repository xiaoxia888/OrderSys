package com.chinasofti.ordersys.dao.admin;

import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
import com.chinasofti.util.sec.Passport;

import java.util.ArrayList;

public class UserDao {
        public ArrayList<UserInfo> getByPage(int page, int pageSize) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<UserInfo> list = helper
                .preparedForPageList(
                        "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId order by userId",
                        new Object[0], page, pageSize, UserInfo.class);

        return list;
    }





        public int getMaxPage(int pageSize) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Long rows = (Long)helper.preparedQueryForObject(
                "select count(*) from userinfo", new Object[0]);

        return (int)((rows.longValue() - 1L) / pageSize + 1L);
    }





        public void addUser(UserInfo info) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Passport passport = new Passport();

        helper.executePreparedUpdate(
                "insert into userinfo(userAccount,userPass,role,faceImg) values(?,?,?,?)",
                new Object[] { info.getUserAccount(),
                        passport.md5(info.getUserPass()),
                        new Integer(info.getRoleId()), info.getFaceimg() });
    }








        public void deleteUser(Integer userId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        helper.executePreparedUpdate("delete from userinfo where userId=?",
                new Object[] { userId });
    }








        public void modify(UserInfo info) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Passport passport = new Passport();

        helper.executePreparedUpdate(
                "update userinfo set userPass=?,faceimg=? where userId=?",
                new Object[] { passport.md5(info.getUserPass()),
                        info.getFaceimg(), new Integer(info.getUserId()) });
    }









        public void adminModify(UserInfo info) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        Passport passport = new Passport();

        helper.executePreparedUpdate(
                "update userinfo set userPass=?,faceimg=?,role=? where userId=?",
                new Object[] { passport.md5(info.getUserPass()),
                        info.getFaceimg(), new Integer(info.getRoleId()),
                        new Integer(info.getUserId()) });
    }










        public UserInfo getUserById(Integer userId) {
        JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();

        ArrayList<UserInfo> list = helper
                .preparedQueryForList(
                        "select userId,userAccount,userPass,locked,roleId,roleName,faceimg from userinfo,roleinfo where userinfo.role=roleinfo.roleId and userId=?",
                        new Object[] { userId }, UserInfo.class);

        return list.get(0);
    }
}



