package com.chinasofti.ordersys.listeners;

import com.chinasofti.ordersys.vo.UserInfo;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


























public class OrderSysListener
  implements HttpSessionListener, HttpSessionAttributeListener
{
  public static Hashtable<String, UserInfo> sessions = new Hashtable<>();



  public static int onlineSessions = 0;







  public static ArrayList<UserInfo> getOnlineWaiters() {
    return getOnlineUsersByRoleId(3);
  }







  public static ArrayList<UserInfo> getOnlineKitchens() {
    return getOnlineUsersByRoleId(2);
  }









  private static ArrayList<UserInfo> getOnlineUsersByRoleId(int roleId) {
    Set<String> sessionIds = sessions.keySet();

    Iterator<String> sessionIdIt = sessionIds.iterator();

    ArrayList<UserInfo> list = new ArrayList<>();

    while (sessionIdIt.hasNext()) {

      UserInfo info = sessions.get(sessionIdIt.next());

      if (info.getRoleId() == roleId)
      {
        list.add(info);
      }
    }

    return list;
  }











  public void attributeAdded(HttpSessionBindingEvent arg0) {
    if (arg0.getName().equals("USER_INFO"))
    {
      sessions.put(arg0.getSession().getId(), (UserInfo)arg0.getValue());
    }
  }










  public void attributeRemoved(HttpSessionBindingEvent arg0) {
    if (arg0.getName().equals("USER_INFO"))
    {
      sessions.remove(arg0.getSession().getId());
    }
  }











  public void attributeReplaced(HttpSessionBindingEvent arg0) {
    if (arg0.getName().equals("USER_INFO"))
    {
      sessions.put(arg0.getSession().getId(), (UserInfo)arg0.getValue());
    }
  }








  public void sessionCreated(HttpSessionEvent arg0) {
    onlineSessions++;
  }







  public void sessionDestroyed(HttpSessionEvent arg0) {
    onlineSessions--;

    if (arg0.getSession().getAttribute("USER_INFO") != null)
    {
      sessions.remove(arg0.getSession().getId());
    }
  }
}

