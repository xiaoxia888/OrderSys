/*     */ package com.chinasofti.util.jdbc.datasource;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLFeatureNotSupportedException;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Logger;
/*     */ import javax.sql.DataSource;
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
/*     */ public class ICSSSimpleDatasource
/*     */   implements DataSource
/*     */ {
/*  41 */   private Vector<DSConnectionContext> dbPool = new Vector<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private int maxConnections = 100;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private int waitTimeOut = 300;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private int incrementalConnections = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private int initConnections = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private int waitTimes = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private String driverString = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private String conString = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private String dbUser = "";
/*     */ 
/*     */ 
/*     */   
/*  85 */   private String dbPass = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector<DSConnectionContext> getDbPool() {
/*  93 */     return this.dbPool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxConnections(int maxConnections) {
/* 103 */     this.maxConnections = maxConnections;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWaitTimeOut(int waitTimeOut) {
/* 113 */     this.waitTimeOut = waitTimeOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncrementalConnections(int incrementalConnections) {
/* 123 */     this.incrementalConnections = incrementalConnections;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitConnections(int initConnections) {
/* 133 */     this.initConnections = initConnections;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWaitTimes(int waitTimes) {
/* 143 */     this.waitTimes = waitTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDriverString(String driverString) {
/* 153 */     this.driverString = driverString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConString(String conString) {
/* 163 */     this.conString = conString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDbUser(String dbUser) {
/* 173 */     this.dbUser = dbUser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDbPass(String dbPass) {
/* 183 */     this.dbPass = dbPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 191 */     this.dbPool = new Vector<>();
/*     */     
/* 193 */     createConnections(this.initConnections);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createConnections(int incremental) {
/* 204 */     for (int i = 0; i < incremental; i++) {
/*     */       
/* 206 */       if (this.dbPool.size() >= this.maxConnections) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 212 */       DSConnectionContext conT = createConnection();
/* 213 */       if (conT != null)
/*     */       {
/* 215 */         this.dbPool.addElement(conT);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DSConnectionContext createConnection() {
/*     */     try {
/* 228 */       Class.forName(this.driverString);
/*     */       
/* 230 */       Connection con = DriverManager.getConnection(this.conString, this.dbUser, 
/* 231 */           this.dbPass);
/*     */       
/* 233 */       if (this.dbPool.size() == 0) {
/*     */         
/* 235 */         DatabaseMetaData metaData = con.getMetaData();
/*     */         
/* 237 */         int driverMaxConnection = metaData.getMaxConnections();
/*     */         
/* 239 */         if (driverMaxConnection > 0 && 
/* 240 */           this.maxConnections > driverMaxConnection) {
/* 241 */           setMaxConnections(driverMaxConnection);
/*     */         }
/*     */       } 
/*     */       
/* 245 */       DSConnectionContext conT = new DSConnectionContext(con);
/*     */       
/* 247 */       DSConnectionInvocationHandler conHandle = new DSConnectionInvocationHandler(
/* 248 */           conT);
/*     */       
/* 250 */       Connection proxyCon = (Connection)Proxy.newProxyInstance(con
/* 251 */           .getClass().getClassLoader(), 
/* 252 */           new Class[] { Connection.class }, conHandle);
/*     */       
/* 254 */       conT.proxyConnection = proxyCon;
/*     */       
/* 256 */       return conT;
/* 257 */     } catch (Exception e) {
/*     */       
/* 259 */       e.printStackTrace();
/*     */       
/* 261 */       return null;
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
/*     */   private void clientWait(int mSeconds) {
/*     */     try {
/* 275 */       Thread.sleep(mSeconds);
/* 276 */     } catch (InterruptedException e) {
/*     */       
/* 278 */       e.printStackTrace();
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
/*     */   public Connection getConnection() throws SQLException {
/* 292 */     if (this.dbPool == null) {
/* 293 */       return null;
/*     */     }
/*     */     
/* 296 */     Connection con = getFreeConnection();
/*     */     
/* 298 */     int wTimes = 0;
/*     */     
/* 300 */     while (con == null && wTimes < this.waitTimes) {
/*     */       
/* 302 */       wTimes++;
/*     */       
/* 304 */       clientWait(this.waitTimeOut);
/*     */       
/* 306 */       con = getFreeConnection();
/*     */     } 
/*     */     
/* 309 */     return con;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Connection getFreeConnection() {
/* 319 */     Connection con = findFreeConnection();
/*     */     
/* 321 */     if (con == null) {
/*     */       
/* 323 */       createConnections(this.incrementalConnections);
/*     */       
/* 325 */       con = findFreeConnection();
/*     */       
/* 327 */       if (con == null) {
/* 328 */         return null;
/*     */       }
/*     */     } 
/*     */     
/* 332 */     return con;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Connection findFreeConnection() {
/* 343 */     DSConnectionContext conT = null;
/*     */ 
/*     */     
/*     */     try {
/* 347 */       for (int i = 0; i < this.dbPool.size(); i++) {
/*     */         
/* 349 */         conT = this.dbPool.elementAt(i);
/*     */         
/* 351 */         if (!conT.busyFlag) {
/*     */           
/* 353 */           conT.busyFlag = true;
/*     */           
/* 355 */           System.out.println("使用第" + i + "个连接处理数据");
/*     */           
/* 357 */           return conT.proxyConnection;
/*     */         } 
/*     */       } 
/*     */       
/* 361 */       return null;
/* 362 */     } catch (Exception ex) {
/*     */       
/* 364 */       ex.printStackTrace();
/*     */       
/* 366 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PrintWriter getLogWriter() throws SQLException {
/* 373 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogWriter(PrintWriter out) throws SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginTimeout(int seconds) throws SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLoginTimeout() throws SQLException {
/* 391 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 397 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 403 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connection getConnection(String username, String password) throws SQLException {
/* 410 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 416 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\datasource\ICSSSimpleDatasource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */