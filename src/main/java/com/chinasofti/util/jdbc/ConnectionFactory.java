package com.chinasofti.util.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {
    private static ThreadLocal<Connection> local=new ThreadLocal<Connection>();
    private static DataSource dataSource;
    //加载数据库配置文件，并创建数据库连接池
    static {
        try {
            Properties pro = new Properties();
            pro.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取连接
    public static synchronized Connection getConnection()throws Exception{
        Connection connection = local.get();
        if(connection==null){
            connection=dataSource.getConnection();
            local.set(connection);
        }
        return connection;
    }
    //关闭方法
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws Exception {
        if(connection!=null){
            connection.close();
            local.remove();
            connection=null;
        }
        if(statement!=null){
            statement.close();
        }
        if(resultSet!=null){
            resultSet.close();
        }
    }

}
