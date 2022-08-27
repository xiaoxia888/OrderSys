package com.chinasofti.util.web.serverpush;

import java.util.Hashtable;

public interface MessageHandler {
  void handle(Hashtable<ServerPushKey, Message> paramHashtable, ServerPushKey paramServerPushKey, Message paramMessage);
}

