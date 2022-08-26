/*    */ package com.chinasofti.ordersys.service;
/*    */ 
/*    */ import com.chinasofti.web.common.httpequest.HttpRequestContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DomainProtectedService
/*    */ {
/*    */   public boolean isFromSameDomain() {
/* 36 */     HttpServletRequest request = HttpRequestContext.getRequest();
/*    */     
/* 38 */     String path = request.getContextPath();
    System.out.println(path);
/*    */     
/* 40 */     String basePath = String.valueOf(request.getScheme()) + "://" + request.getServerName() + 
/* 41 */       ":" + request.getServerPort() + path + "/";
/*    */     
/* 43 */     String fromUrl = request.getHeader("referer");
/*    */     
/* 45 */     return (fromUrl != null && fromUrl.startsWith(basePath));
/*    */   }
/*    */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\service\DomainProtectedService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */