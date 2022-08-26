package com.chinasofti.util.jdbc.template.specialsqloperation;

import java.sql.PreparedStatement;

public abstract class SpecialSQLOperation {
  public abstract String getTopNSQL(String paramString, boolean paramBoolean);
  
  public abstract PreparedStatement setTopNQueryParameter(PreparedStatement paramPreparedStatement, Object[] paramArrayOfObject, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\specialsqloperation\SpecialSQLOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */