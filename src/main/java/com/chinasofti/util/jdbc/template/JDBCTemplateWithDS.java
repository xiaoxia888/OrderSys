/*     */ package com.chinasofti.util.jdbc.template;
/*     */ 
/*     */ import com.chinasofti.util.jdbc.datasource.ICSSSimpleDatasource;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
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
/*     */ public class JDBCTemplateWithDS
/*     */   extends JDBCTemplate
/*     */ {
/*     */   private static JDBCTemplateWithDS instance;
/*     */   ICSSSimpleDatasource dS;
/*     */   
/*     */   JDBCTemplateWithDS(String driverName, String connectionString, String dbmsUserName, String dbmsPassword) {
/*  52 */     super(driverName, connectionString, dbmsUserName, dbmsPassword);
/*     */     
/*  54 */     this.dS = new ICSSSimpleDatasource();
/*     */     
/*  56 */     this.dS.setConString(connectionString);
/*     */     
/*  58 */     this.dS.setDriverString(driverName);
/*     */     
/*  60 */     this.dS.setDbUser(dbmsUserName);
/*     */     
/*  62 */     this.dS.setDbPass(dbmsPassword);
/*     */     
/*  64 */     this.dS.init();
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
/*     */   public Connection getConnection() {
/*     */     try {
/*  78 */       return this.dS.getConnection();
/*  79 */     } catch (SQLException e) {
/*     */ 
/*     */       
/*  82 */       e.printStackTrace();
/*     */       
/*  84 */       return null;
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
/*     */   public static JDBCTemplateWithDS getJDBCHelper(String configFilePath) {
/*  97 */     if (instance == null) {
/*     */       
/*     */       try {
/* 100 */         String driverName = "";
/*     */         
/* 102 */         String connectionString = "";
/*     */         
/* 104 */         String dbmsUserName = "";
/*     */         
/* 106 */         String dbmsPassword = "";
/*     */ 
/*     */         
/* 109 */         DocumentBuilder db = DocumentBuilderFactory.newInstance()
/* 110 */           .newDocumentBuilder();
/*     */         
/* 112 */         Document doc = db.parse(configFilePath);
/*     */         
/* 114 */         Node jdbcNode = doc.getElementsByTagName("jdbc").item(0);
/*     */         
/* 116 */         NodeList args = jdbcNode.getChildNodes();
/*     */         
/* 118 */         for (int i = 0; i < args.getLength(); i++) {
/*     */           
/* 120 */           Node arg = args.item(i);
/*     */           
/* 122 */           if ("drivername".equals(arg.getNodeName())) {
/* 123 */             driverName = arg.getTextContent().trim();
/*     */           }
/* 125 */           else if ("connectionstring".equals(arg.getNodeName())) {
/* 126 */             connectionString = arg.getTextContent().trim();
/*     */           }
/* 128 */           else if ("dbmsusername".equals(arg.getNodeName())) {
/* 129 */             dbmsUserName = arg.getTextContent().trim();
/*     */           }
/* 131 */           else if ("dbmspassword".equals(arg.getNodeName())) {
/* 132 */             dbmsPassword = arg.getTextContent().trim();
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 138 */         instance = new JDBCTemplateWithDS(driverName, connectionString, 
/* 139 */             dbmsUserName, dbmsPassword);
/*     */       }
/* 141 */       catch (Exception e) {
/* 142 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 146 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JDBCTemplateWithDS getJDBCHelper() {
/* 156 */     return getJDBCHelper(Thread.currentThread().getContextClassLoader()
/* 157 */         .getResource("jdbc.xml").getPath());
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\JDBCTemplateWithDS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */