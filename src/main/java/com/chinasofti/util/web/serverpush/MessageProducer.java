 package com.chinasofti.util.web.serverpush;












 public class MessageProducer
 {
   public void sendMessage(String sessionID, String messageTitle, String msg) {
     ServerPushKey key = new ServerPushKey(sessionID, messageTitle);

     if (ServerPushMQ.waitQueue.get(key) != null) {

       Message message = ServerPushMQ.waitQueue.get(key);

       //System.out.println("MessageProducer"+msg);
       message.setMsg(msg);

       synchronized (message) {

         message.notifyAll();
       }
     }
   }
 }


