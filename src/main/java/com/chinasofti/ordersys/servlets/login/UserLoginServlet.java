/*     */ package com.chinasofti.ordersys.servlets.login;
/*     */ 
/*     */ import com.chinasofti.ordersys.service.DomainProtectedService;
/*     */ import com.chinasofti.ordersys.service.login.LoginService;
/*     */ import com.chinasofti.ordersys.vo.UserInfo;
/*     */ import com.chinasofti.util.sec.Passport;
/*     */ import com.chinasofti.util.web.upload.MultipartRequestParser;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class UserLoginServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  50 */     doPost(request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */   
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  64 */     MultipartRequestParser parser = new MultipartRequestParser();
/*     */     
/*  66 */     UserInfo info = (UserInfo)parser.parse(request, UserInfo.class);
/*     */     
/*  68 */     Passport passport = new Passport();
/*     */     
/*  70 */     info.setUserPass(passport.md5(info.getUserPass()));
/*     */     
/*  72 */     DomainProtectedService domainService = new DomainProtectedService();
/*     */
/*  74 */     if (domainService.isFromSameDomain()) {
/*     */       
/*  76 */       LoginService loginService = new LoginService();
/*     */
             //System.out.println(loginService.login(info));
/*  78 */       switch (loginService.login(info)) {

/*     */ 
/*     */         
/*     */         case 0:
/*  82 */           request.setAttribute("ERROR_MSG", "用户名不存在！");
/*     */           
/*  84 */           request.setAttribute("USER_INFO", info);
/*     */           
/*  86 */           request.getRequestDispatcher("/pages/login.jsp").forward(
/*  87 */               (ServletRequest)request, (ServletResponse)response);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  92 */           request.setAttribute("ERROR_MSG", "用户密码不匹配！");
/*     */           
/*  94 */           request.setAttribute("USER_INFO", info);
/*     */           
/*  96 */           request.getRequestDispatcher("/pages/login.jsp").forward(
/*  97 */               (ServletRequest)request, (ServletResponse)response);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 102 */           request.getSession().setAttribute("USER_INFO", 
/* 103 */               loginService.getLoginUser());
                //System.out.println(loginService.getLoginUser());
/*     */           
/* 105 */           switch (loginService.getLoginUser().getRoleId()) {
/*     */ 
/*     */             
/*     */             case 1:
/* 109 */               response.sendRedirect("/OrderSys/toadminmain.order");
/*     */               break;
/*     */ 
/*     */             
/*     */             case 2:
/* 114 */               response.sendRedirect("/OrderSys/tokitchenmain.order");
/*     */               break;
/*     */ 
/*     */             
/*     */             case 3:
/* 119 */               response.sendRedirect("/OrderSys/towaitermain.order");
/*     */               break;
/*     */           } 
/*     */ 
/*     */           
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 128 */           request.setAttribute("ERROR_MSG", "该用户已经被锁定！");
/*     */           
/* 130 */           request.setAttribute("USER_INFO", loginService.getLoginUser());
/*     */           
/* 132 */           request.getRequestDispatcher("/pages/login.jsp").forward(
/* 133 */               (ServletRequest)request, (ServletResponse)response);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 138 */           request.setAttribute("ERROR_MSG", "该用户已经在线，不能重复登录！");
/*     */           
/* 140 */           request.setAttribute("USER_INFO", info);
/*     */           
/* 142 */           request.getRequestDispatcher("/pages/login.jsp").forward(
/* 143 */               (ServletRequest)request, (ServletResponse)response);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 149 */       request.getSession().setAttribute("USER_INFO", info);
/*     */       
/* 151 */       response.sendRedirect("todomainerror.order");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\servlets\login\UserLoginServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */