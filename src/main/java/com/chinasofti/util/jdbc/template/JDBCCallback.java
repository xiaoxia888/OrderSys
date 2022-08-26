package com.chinasofti.util.jdbc.template;

import java.sql.Statement;

public interface JDBCCallback {
  Object doWithStatement(Statement paramStatement);
}


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\JDBCCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */