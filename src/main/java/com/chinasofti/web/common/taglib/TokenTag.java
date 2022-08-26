/*     */ package com.chinasofti.web.common.taglib;
/*     */ 
/*     */ import com.chinasofti.web.common.httpequest.HttpRequestContext;
/*     */ import java.io.IOException;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TokenTag
/*     */   extends TagSupport
/*     */ {
/*     */   public static final String TOKEN_SESSION_ATTR_NAME = "SUBMIT_TOKEN_ATTR_NAME_SESSION";
/*     */   public static final String TOKEN_REQUEST_ATTR_NAME = "SUBMIT_TOKEN_ATTR_NAME_REQUEST";
/*     */   
/*     */   public static boolean isTokenValid() {
    System.out.println(HttpRequestContext.getRequest().getParameter("userAccount"));
    /*  49 */     String requestToken = HttpRequestContext.getRequest().getParameter(
/*  50 */         "SUBMIT_TOKEN_ATTR_NAME_REQUEST");
/*     */     
/*  52 */     Object sessionToken = HttpRequestContext.getRequest().getSession()
/*  53 */       .getAttribute("SUBMIT_TOKEN_ATTR_NAME_SESSION");
/*     */ 
/*     */ 
/*     */     
/*  57 */     return (sessionToken != null && 
/*  58 */       sessionToken.toString().equals(requestToken));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void releaseToken() {
/*  66 */     HttpRequestContext.getRequest().getSession()
/*  67 */       .setAttribute("SUBMIT_TOKEN_ATTR_NAME_SESSION", "");
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
/*     */   public int doEndTag() throws JspException {
/*  80 */     String token = UUID.randomUUID().toString();
/*     */     
/*  82 */     this.pageContext.getSession().setAttribute("SUBMIT_TOKEN_ATTR_NAME_SESSION", token);
/*     */     
/*  84 */     String tokenTag = "<input type=\"hidden\" name=\"SUBMIT_TOKEN_ATTR_NAME_REQUEST\" value=\"" + 
/*  85 */       token + "\"/>";
/*     */     
/*     */     try {
/*  88 */       this.pageContext.getOut().print(tokenTag);
/*     */     }
/*  90 */     catch (IOException e) {
/*     */ 
/*     */       
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  96 */     return 6;
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
/*     */   public int doStartTag() throws JspException {
/* 108 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\web\common\taglib\TokenTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */