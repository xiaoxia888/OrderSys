/*     */ package com.chinasofti.util.jdbc.template;
/*     */ 
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBCTemplateFactory
/*     */ {
/*     */   private static JDBCTemplate instance;
/*     */   
/*     */   public static JDBCTemplate getJDBCHelper(String configFilePath) {
/*  51 */     if (instance == null) {
/*     */       
/*     */       try {
/*  54 */         String driverName = "";
/*     */         
/*  56 */         String connectionString = "";
/*     */         
/*  58 */         String dbmsUserName = "";
/*     */         
/*  60 */         String dbmsPassword = "";
/*     */ 
/*     */         
/*  63 */         DocumentBuilder db = DocumentBuilderFactory.newInstance()
/*  64 */           .newDocumentBuilder();
/*     */         
/*  66 */         Document doc = db.parse(configFilePath);
/*     */         
/*  68 */         Node jdbcNode = doc.getElementsByTagName("jdbc").item(0);
/*     */         
/*  70 */         NodeList args = jdbcNode.getChildNodes();
/*     */         
/*  72 */         for (int i = 0; i < args.getLength(); i++) {
/*     */           
/*  74 */           Node arg = args.item(i);
/*     */           
/*  76 */           if ("drivername".equals(arg.getNodeName())) {
/*  77 */             driverName = arg.getTextContent().trim();
/*     */           }
/*  79 */           else if ("connectionstring".equals(arg.getNodeName())) {
/*  80 */             connectionString = arg.getTextContent().trim();
/*     */           }
/*  82 */           else if ("dbmsusername".equals(arg.getNodeName())) {
/*  83 */             dbmsUserName = arg.getTextContent().trim();
/*     */           }
/*  85 */           else if ("dbmspassword".equals(arg.getNodeName())) {
/*  86 */             dbmsPassword = arg.getTextContent().trim();
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  92 */         instance = new JDBCTemplate(driverName, 
/*  93 */             connectionString, dbmsUserName, dbmsPassword);
/*     */       }
/*  95 */       catch (Exception e) {
/*  96 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 100 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JDBCTemplate getJDBCHelper() {
/* 109 */     return getJDBCHelper(Thread.currentThread().getContextClassLoader().getResource("jdbc.xml").getPath());
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\JDBCTemplateFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */