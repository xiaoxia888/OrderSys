package com.chinasofti.util.web.serverpush;

import java.util.Hashtable;

public interface MessageHandler {
  void handle(Hashtable<ServerPushKey, Message> paramHashtable, ServerPushKey paramServerPushKey, Message paramMessage);
}


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\web\serverpush\MessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */