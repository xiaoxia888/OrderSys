/*     */ package com.chinasofti.ordersys.listeners;
/*     */ 
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpSessionAttributeListener;
/*     */ import javax.servlet.http.HttpSessionBindingEvent;
/*     */ import javax.servlet.http.HttpSessionEvent;
/*     */ import javax.servlet.http.HttpSessionListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OrderSysListener
/*     */   implements HttpSessionListener, HttpSessionAttributeListener
/*     */ {
/*  41 */   public static Hashtable<String, UserInfo> sessions = new Hashtable<>();
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static int onlineSessions = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<UserInfo> getOnlineWaiters() {
/*  54 */     return getOnlineUsersByRoleId(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<UserInfo> getOnlineKitchens() {
/*  64 */     return getOnlineUsersByRoleId(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<UserInfo> getOnlineUsersByRoleId(int roleId) {
/*  76 */     Set<String> sessionIds = sessions.keySet();
/*     */     
/*  78 */     Iterator<String> sessionIdIt = sessionIds.iterator();
/*     */     
/*  80 */     ArrayList<UserInfo> list = new ArrayList<>();
/*     */     
/*  82 */     while (sessionIdIt.hasNext()) {
/*     */       
/*  84 */       UserInfo info = sessions.get(sessionIdIt.next());
/*     */       
/*  86 */       if (info.getRoleId() == roleId)
/*     */       {
/*  88 */         list.add(info);
/*     */       }
/*     */     } 
/*     */     
/*  92 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeAdded(HttpSessionBindingEvent arg0) {
/* 106 */     if (arg0.getName().equals("USER_INFO"))
/*     */     {
/* 108 */       sessions.put(arg0.getSession().getId(), (UserInfo)arg0.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeRemoved(HttpSessionBindingEvent arg0) {
/* 122 */     if (arg0.getName().equals("USER_INFO"))
/*     */     {
/* 124 */       sessions.remove(arg0.getSession().getId());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeReplaced(HttpSessionBindingEvent arg0) {
/* 139 */     if (arg0.getName().equals("USER_INFO"))
/*     */     {
/* 141 */       sessions.put(arg0.getSession().getId(), (UserInfo)arg0.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sessionCreated(HttpSessionEvent arg0) {
/* 156 */     onlineSessions++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sessionDestroyed(HttpSessionEvent arg0) {
/* 169 */     onlineSessions--;
/*     */     
/* 171 */     if (arg0.getSession().getAttribute("USER_INFO") != null)
/*     */     {
/* 173 */       sessions.remove(arg0.getSession().getId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\listeners\OrderSysListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */