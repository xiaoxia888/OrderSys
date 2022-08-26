/*      */ package com.chinasofti.util.jdbc.template;
/*      */ 
/*      */ import com.chinasofti.util.bean.BeanUtil;
/*      */ import com.chinasofti.util.jdbc.template.specialsqloperation.MySQLSpecialOperation;
/*      */ import com.chinasofti.util.jdbc.template.specialsqloperation.OracleSpecialOperation;
/*      */ import com.chinasofti.util.jdbc.template.specialsqloperation.SpecialSQLOperation;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JDBCTemplate
/*      */ {
/*   51 */   private static Hashtable<String, Class<? extends SpecialSQLOperation>> driverDBMSMapping = new Hashtable<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addDriverDBMSMapping(String driverName, Class<? extends SpecialSQLOperation> operationClass) {
/*   64 */     driverDBMSMapping.put(driverName, operationClass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   70 */   private BeanUtil beanUtil = new BeanUtil();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String driverName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String connectionString;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String dbmsUserName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String dbmsPassword;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDriverName(String driverName) {
/*   99 */     this.driverName = driverName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionString(String connectionString) {
/*  109 */     this.connectionString = connectionString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDbmsUserName(String dbmsUserName) {
/*  119 */     this.dbmsUserName = dbmsUserName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDbmsPassword(String dbmsPassword) {
/*  129 */     this.dbmsPassword = dbmsPassword;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Connection getConnection() {
/*      */     try {
/*  141 */       Class.forName(this.driverName);
/*      */       
/*  143 */       Connection con = DriverManager.getConnection(this.connectionString, 
/*  144 */           this.dbmsUserName, this.dbmsPassword);
/*      */ 
/*      */       
/*  147 */       return con;
/*  148 */     } catch (Exception e) {
/*      */       
/*  150 */       e.printStackTrace();
/*      */       
/*  152 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JDBCTemplate(String driverName, String connectionString, String dbmsUserName, String dbmsPassword) {
/*  173 */     this.driverName = driverName;
/*      */     
/*  175 */     this.connectionString = connectionString;
/*      */     
/*  177 */     this.dbmsUserName = dbmsUserName;
/*      */     
/*  179 */     this.dbmsPassword = dbmsPassword;
/*      */     
/*  181 */     addDriverDBMSMapping("com.mysql.jdbc.Driver", 
/*  182 */         (Class)MySQLSpecialOperation.class);
/*      */     
/*  184 */     addDriverDBMSMapping("oracle.jdbc.dirver.OracleDriver", 
/*  185 */         (Class)OracleSpecialOperation.class);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object execute(JDBCCallback callback) {
/*  198 */     Object result = null;
/*  199 */     Connection con = null;
/*  200 */     Statement statement = null;
/*      */     
/*      */     try {
/*  203 */       con = getConnection();
/*      */       
/*  205 */       statement = con.createStatement();
/*      */       
/*  207 */       result = callback.doWithStatement(statement);
/*      */     }
/*  209 */     catch (Exception e) {
/*      */       
/*  211 */       e.printStackTrace();
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  216 */       if (statement != null) {
/*      */         try {
/*  218 */           statement.close();
/*  219 */         } catch (SQLException e) {
/*      */           
/*  221 */           e.printStackTrace();
/*      */         } 
/*      */       }
/*      */       
/*  225 */       if (con != null) {
/*      */         try {
/*  227 */           con.close();
/*  228 */         } catch (SQLException e) {
/*      */           
/*  230 */           e.printStackTrace();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  236 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object execute(String preparedSQL, JDBCCallback callback) {
/*  248 */     Object result = null;
/*  249 */     Connection con = null;
/*  250 */     PreparedStatement statement = null;
/*      */     
/*      */     try {
/*  253 */       con = getConnection();
/*      */ 
/*      */       
/*  256 */       statement = con.prepareStatement(preparedSQL, 
/*  257 */           1);
/*      */ 
/*      */       
/*  260 */       result = callback.doWithStatement(statement);
/*      */       
/*  262 */       statement.close();
/*      */       
/*  264 */       con.close();
/*  265 */     } catch (Exception e) {
/*      */ 
/*      */       
/*  268 */       e.printStackTrace();
/*      */     } finally {
/*      */       
/*  271 */       if (con != null) {
/*      */         try {
/*  273 */           con.close();
/*  274 */         } catch (SQLException e) {
/*      */           
/*  276 */           e.printStackTrace();
/*      */         } 
/*      */       }
/*  279 */       if (statement != null) {
/*      */         try {
/*  281 */           con.close();
/*  282 */         } catch (SQLException e) {
/*      */           
/*  284 */           e.printStackTrace();
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  290 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int executeUpdate(final String sql) {
/*  303 */     JDBCCallback updateCallback = new JDBCCallback()
/*      */       {
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*  308 */           int result = -1;
/*      */           
/*      */           try {
/*  311 */             result = statement.executeUpdate(sql);
/*  312 */           } catch (Exception e) {
/*      */             
/*  314 */             e.printStackTrace();
/*      */           } 
/*      */           
/*  317 */           return new Integer(result);
/*      */         }
/*      */       };
/*      */     
/*  321 */     return ((Integer)execute(updateCallback)).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int executePreparedUpdate(String preparedSQL, final Object[] data) {
/*  335 */     JDBCCallback updateCallback = new JDBCCallback()
/*      */       {
/*      */ 
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*      */           try {
/*  342 */             PreparedStatement pstatement = (PreparedStatement)statement;
/*      */             
/*  344 */             for (int i = 0; i < data.length; i++)
/*      */             {
/*  346 */               pstatement.setObject(i + 1, data[i]);
/*      */             }
/*      */             
/*  349 */             int result = pstatement.executeUpdate();
/*      */             
/*  351 */             return new Integer(result);
/*      */           }
/*  353 */           catch (Exception e) {
/*      */             
/*  355 */             e.printStackTrace();
/*      */             
/*  357 */             return new Integer(-1);
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  363 */     return ((Integer)execute(preparedSQL, updateCallback)).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] executeBatch(final String[] batchSQLs) {
/*  374 */     JDBCCallback batchCallback = new JDBCCallback() {
/*      */         public Object doWithStatement(Statement statement) {
/*      */           try {
/*      */             byte b;
/*      */             int i;
/*      */             String[] arrayOfString;
/*  380 */             for (i = (arrayOfString = batchSQLs).length, b = 0; b < i; ) { String sql = arrayOfString[b];
/*      */               
/*  382 */               statement.addBatch(sql);
/*      */               b++; }
/*      */             
/*  385 */             return statement.executeBatch();
/*  386 */           } catch (Exception e) {
/*      */             
/*  388 */             e.printStackTrace();
/*      */             
/*  390 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  396 */     return (int[])execute(batchCallback);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void executePreparedBatch(String preparedSQL, final List<Object[]> data, final int batchSize) {
/*  414 */     JDBCCallback batchCallback = new JDBCCallback()
/*      */       {
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*      */           try {
/*  420 */             PreparedStatement pstatement = (PreparedStatement)statement;
/*      */             
/*  422 */             for (int i = 0; i < data.size(); i++) {
/*      */               
/*  424 */               Object[] updateData = data.get(i);
/*      */               
/*  426 */               for (int index = 0; index < updateData.length; index++)
/*      */               {
/*  428 */                 pstatement.setObject(index + 1, updateData[index]);
/*      */               }
/*      */               
/*  431 */               pstatement.addBatch();
/*      */               
/*  433 */               if (i % batchSize == 0) {
/*      */                 
/*  435 */                 pstatement.executeBatch();
/*      */                 
/*  437 */                 pstatement.clearBatch();
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  442 */             return pstatement.executeBatch();
/*  443 */           } catch (Exception e) {
/*      */             
/*  445 */             e.printStackTrace();
/*      */             
/*  447 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*  454 */     execute(preparedSQL, batchCallback);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void executeMixedBatch(String preparedSQL, final List<Object[]> data, final int batchSize, final String[] batchSQLs) {
/*  473 */     JDBCCallback batchCallback = new JDBCCallback()
/*      */       {
/*      */ 
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*      */           try {
/*  480 */             PreparedStatement pstatement = (PreparedStatement)statement;
/*      */             
/*  482 */             for (int i = 0; i < data.size(); i++) {
/*      */               
/*  484 */               Object[] updateData = data.get(i);
/*      */               
/*  486 */               for (int index = 0; index < updateData.length; index++)
/*      */               {
/*  488 */                 pstatement.setObject(index + 1, updateData[index]);
/*      */               }
/*      */               
/*  491 */               pstatement.addBatch();
/*      */               
/*  493 */               if (i % batchSize == 0) {
/*      */                 
/*  495 */                 pstatement.executeBatch();
/*      */                 
/*  497 */                 pstatement.clearBatch();
/*      */               } 
/*      */             }  byte b; int j;
/*      */             String[] arrayOfString;
/*  501 */             for (j = (arrayOfString = batchSQLs).length, b = 0; b < j; ) { String sql = arrayOfString[b];
/*      */               
/*  503 */               pstatement.addBatch(sql);
/*      */               b++; }
/*      */             
/*  506 */             pstatement.executeBatch();
/*      */             
/*  508 */             return null;
/*  509 */           } catch (Exception e) {
/*      */             
/*  511 */             e.printStackTrace();
/*      */             
/*  513 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/*      */     
/*  518 */     execute(preparedSQL, batchCallback);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> ArrayList<T> queryForList(final String sql, final Class<T> persistenceClass) {
/*  533 */     JDBCCallback queryCallback = new JDBCCallback()
/*      */       {
/*      */ 
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*      */           try {
/*  540 */             ResultSet rs = statement.executeQuery(sql);
/*      */             
/*  542 */             ArrayList<T> resultList = JDBCTemplate.this.resultSet2List(rs, 
/*  543 */                 persistenceClass);
/*      */             
/*  545 */             rs.close();
/*      */             
/*  547 */             return resultList;
/*  548 */           } catch (Exception e) {
/*      */             
/*  550 */             e.printStackTrace();
/*      */             
/*  552 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/*      */     
/*  557 */     return (ArrayList<T>)execute(queryCallback);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> ArrayList<T> preparedQueryForList(String preparedSQL, final Object[] args, final Class<T> persistenceClass) {
/*  576 */     JDBCCallback queryCallback = new JDBCCallback()
/*      */       {
/*      */ 
/*      */         
/*      */         public Object doWithStatement(Statement statement)
/*      */         {
/*      */           try {
/*  583 */             PreparedStatement pstatement = (PreparedStatement)statement;
/*      */             
/*  585 */             for (int i = 0; i < args.length; i++)
/*      */             {
/*  587 */               pstatement.setObject(i + 1, args[i]);
/*      */             }
/*      */             
/*  590 */             ResultSet rs = pstatement.executeQuery();
/*      */             
/*  592 */             ArrayList<T> resultList = JDBCTemplate.this.resultSet2List(rs, 
/*  593 */                 persistenceClass);
/*      */             
/*  595 */             rs.close();
/*      */             
/*  597 */             return resultList;
/*      */           }
/*  599 */           catch (Exception e) {
/*      */             
/*  601 */             e.printStackTrace();
/*      */             
/*  603 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  609 */     return (ArrayList<T>)execute(preparedSQL, queryCallback);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object queryForObject(final String sql) {
/*  622 */     Object returnObj = execute(new JDBCCallback()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*      */             try {
/*  630 */               ResultSet rs = statement.executeQuery(sql);
/*      */               
/*  632 */               rs.next();
/*      */               
/*  634 */               Object obj = rs.getObject(1);
/*      */               
/*  636 */               rs.close();
/*      */               
/*  638 */               return obj;
/*      */             }
/*  640 */             catch (Exception ex) {
/*      */               
/*  642 */               ex.printStackTrace();
/*      */               
/*  644 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  649 */     return returnObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] queryForObjectArray(final String sql) {
/*  662 */     Object[] array = (Object[])execute(new JDBCCallback()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*      */             try {
/*  670 */               ResultSet rs = statement.executeQuery(sql);
/*      */ 
/*      */ 
/*      */               
/*  674 */               Object[] resultArray = JDBCTemplate.this.singleLineResut2ObjectArray(rs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  680 */               rs.close();
/*      */               
/*  682 */               return resultArray;
/*      */             }
/*  684 */             catch (Exception ex) {
/*      */               
/*  686 */               ex.printStackTrace();
/*      */               
/*  688 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  693 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] preparedQueryForObjectArray(String preparedSQL, final Object[] args) {
/*  708 */     Object[] array = (Object[])execute(preparedSQL, new JDBCCallback()
/*      */         {
/*      */ 
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*  714 */             PreparedStatement stmt = (PreparedStatement)statement;
/*      */ 
/*      */             
/*      */             try {
/*  718 */               for (int i = 0; i < args.length; i++)
/*      */               {
/*  720 */                 stmt.setObject(i + 1, args[i]);
/*      */               }
/*      */               
/*  723 */               ResultSet rs = stmt.executeQuery();
/*      */ 
/*      */ 
/*      */               
/*  727 */               Object[] resultArray = JDBCTemplate.this.singleLineResut2ObjectArray(rs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  733 */               rs.close();
/*      */               
/*  735 */               return resultArray;
/*      */             }
/*  737 */             catch (Exception ex) {
/*      */               
/*  739 */               ex.printStackTrace();
/*      */               
/*  741 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  746 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] insertForGeneratedKeys(final String insertSQL) {
/*  758 */     Object[] generatedKeys = (Object[])execute(new JDBCCallback()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*      */             try {
/*  766 */               statement.executeUpdate(insertSQL, 
/*  767 */                   1);
/*      */               
/*  769 */               ResultSet rs = statement.getGeneratedKeys();
/*      */               
/*  771 */               Object[] keys = JDBCTemplate.this.singleLineResut2ObjectArray(rs);
/*      */               
/*  773 */               rs.close();
/*      */               
/*  775 */               return keys;
/*      */             }
/*  777 */             catch (Exception ex) {
/*      */               
/*  779 */               ex.printStackTrace();
/*      */               
/*  781 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  786 */     return generatedKeys;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] preparedInsertForGeneratedKeys(String preparedInsertSQL, final Object[] args) {
/*  801 */     Object[] generatedKeys = (Object[])execute(preparedInsertSQL, 
/*  802 */         new JDBCCallback()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*      */             try {
/*  810 */               PreparedStatement stmt = (PreparedStatement)statement;
/*      */               
/*  812 */               for (int i = 0; i < args.length; i++)
/*      */               {
/*  814 */                 stmt.setObject(i + 1, args[i]);
/*      */               }
/*      */               
/*  817 */               stmt.executeUpdate();
/*      */               
/*  819 */               ResultSet rs = stmt.getGeneratedKeys();
/*      */               
/*  821 */               Object[] keys = JDBCTemplate.this.singleLineResut2ObjectArray(rs);
/*      */               
/*  823 */               rs.close();
/*      */               
/*  825 */               return keys;
/*      */             }
/*  827 */             catch (Exception e) {
/*      */               
/*  829 */               e.printStackTrace();
/*      */               
/*  831 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  838 */     return generatedKeys;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object[] singleLineResut2ObjectArray(ResultSet rs) {
/*      */     try {
/*  852 */       ResultSetMetaData metaData = rs.getMetaData();
/*      */       
/*  854 */       int columnNum = metaData.getColumnCount();
/*      */       
/*  856 */       Object[] array = new Object[columnNum];
/*      */       
/*  858 */       rs.next();
/*      */       
/*  860 */       for (int i = 0; i < columnNum; i++)
/*      */       {
/*  862 */         array[i] = rs.getObject(i + 1);
/*      */       }
/*      */       
/*  865 */       return array;
/*      */     }
/*  867 */     catch (Exception e) {
/*      */       
/*  869 */       e.printStackTrace();
/*      */       
/*  871 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object preparedQueryForObject(String preparedSQL, final Object[] args) {
/*  888 */     Object returnObj = execute(preparedSQL, new JDBCCallback()
/*      */         {
/*      */           
/*      */           public Object doWithStatement(Statement statement)
/*      */           {
/*  893 */             PreparedStatement stmt = (PreparedStatement)statement;
/*      */ 
/*      */             
/*      */             try {
/*  897 */               for (int i = 0; i < args.length; i++)
/*      */               {
/*  899 */                 stmt.setObject(i + 1, args[i]);
/*      */               }
/*      */               
/*  902 */               ResultSet rs = stmt.executeQuery();
/*      */               
/*  904 */               rs.next();
/*      */               
/*  906 */               Object obj = rs.getObject(1);
/*      */               
/*  908 */               rs.close();
/*      */               
/*  910 */               return obj;
/*      */             }
/*  912 */             catch (SQLException e) {
/*      */ 
/*      */               
/*  915 */               e.printStackTrace();
/*      */               
/*  917 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  924 */     return returnObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> ArrayList<T> preparedForPageList(String preparedSQL, Object[] args, int page, int pageSize, Class<T> persistenceClass) {
/*  946 */     return preparedForOffsetList(preparedSQL, args, (page - 1) * pageSize, 
/*  947 */         pageSize, persistenceClass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> ArrayList<T> preparedForOffsetList(String preparedSQL, final Object[] args, final int offset, final int rowNum, final Class<T> persistenceClass) {
/*  970 */     ArrayList<T> list = new ArrayList<>();
/*      */     
/*  972 */     String modifySQL = getSpecialTopNSQL(preparedSQL);
/*      */ 
/*      */ 
/*      */     
/*  976 */     if (modifySQL == preparedSQL) {
/*      */ 
/*      */ 
/*      */       
/*  980 */       list = (ArrayList<T>)execute(preparedSQL, new JDBCCallback()
/*      */           {
/*      */ 
/*      */             
/*      */             public Object doWithStatement(Statement statement)
/*      */             {
/*  986 */               PreparedStatement stmt = (PreparedStatement)statement;
/*      */ 
/*      */               
/*      */               try {
/*  990 */                 for (int i = 0; i < args.length; i++)
/*      */                 {
/*  992 */                   stmt.setObject(i + 1, args[i]);
/*      */                 }
/*  994 */                 ResultSet rs = stmt.executeQuery();
/*      */                 
/*  996 */                 ArrayList<T> resultList = JDBCTemplate.this.resultSet2List(rs, offset, 
/*  997 */                     rowNum, persistenceClass);
/*      */                 
/*  999 */                 rs.close();
/*      */                 
/* 1001 */                 return resultList;
/*      */               }
/* 1003 */               catch (Exception e) {
/*      */                 
/* 1005 */                 e.printStackTrace();
/*      */                 
/* 1007 */                 return null;
/*      */ 
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */           });
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1019 */       list = (ArrayList<T>)execute(modifySQL, new JDBCCallback()
/*      */           {
/*      */             
/*      */             public Object doWithStatement(Statement statement)
/*      */             {
/* 1024 */               PreparedStatement stmt = (PreparedStatement)statement;
/*      */ 
/*      */               
/*      */               try {
/* 1028 */                 ((SpecialSQLOperation)((Class<SpecialSQLOperation>)JDBCTemplate.driverDBMSMapping
/* 1029 */                   .get(JDBCTemplate.this.driverName))
/* 1030 */                   .newInstance())
/* 1031 */                   .setTopNQueryParameter(stmt, args, offset, 
/* 1032 */                     rowNum);
/*      */                 
/* 1034 */                 ResultSet rs = stmt.executeQuery();
/*      */                 
/* 1036 */                 ArrayList<T> resultList = JDBCTemplate.this.resultSet2List(rs, 
/* 1037 */                     persistenceClass);
/*      */                 
/* 1039 */                 rs.close();
/*      */                 
/* 1041 */                 return resultList;
/*      */               }
/* 1043 */               catch (Exception e) {
/*      */                 
/* 1045 */                 e.printStackTrace();
/*      */                 
/* 1047 */                 return null;
/*      */               } 
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1056 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSpecialTopNSQL(String sql) {
/*      */     try {
/* 1070 */       sql = driverDBMSMapping.containsKey(this.driverName) ? ((SpecialSQLOperation)((Class<SpecialSQLOperation>)driverDBMSMapping
/* 1071 */         .get(this.driverName)).newInstance()).getTopNSQL(sql, true) : sql;
/*      */     }
/* 1073 */     catch (Exception e) {
/*      */ 
/*      */       
/* 1076 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1079 */     return sql;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDriverName() {
/* 1089 */     return this.driverName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <T> ArrayList<T> resultSet2List(ResultSet rs, Class<T> persistenceClass) {
/* 1104 */     ArrayList<T> resultList = new ArrayList<>();
/*      */     
/*      */     try {
/* 1107 */       ResultSetMetaData rsMetaData = rs.getMetaData();
/*      */       
/* 1109 */       int columnCount = rsMetaData.getColumnCount();
/*      */       
/* 1111 */       while (rs.next())
/*      */       {
/* 1113 */         T entityObject = persistenceClass.newInstance();
/*      */         
/* 1115 */         for (int cindex = 1; cindex <= columnCount; cindex++) {
/*      */           Blob blob; Clob clob; Timestamp time;
/* 1117 */           String value, cname = rsMetaData.getColumnName(cindex);
/*      */           
/* 1119 */           switch (rsMetaData.getColumnType(cindex)) {
/*      */ 
/*      */             
/*      */             case 2004:
/* 1123 */               blob = rs.getBlob(cindex);
/*      */               
/* 1125 */               this.beanUtil.setBeanProperty(entityObject, cname, blob, 
/* 1126 */                   true);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 2005:
/* 1131 */               clob = rs.getClob(cindex);
/*      */               
/* 1133 */               this.beanUtil.setBeanProperty(entityObject, cname, clob, 
/* 1134 */                   true);
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case 91:
/*      */             case 92:
/*      */             case 93:
/* 1142 */               time = rs.getTimestamp(cindex);
/* 1143 */               if (time != null) {
/*      */                 
/* 1145 */                 Date date = new Date(time.getTime());
/*      */                 
/* 1147 */                 this.beanUtil.setBeanProperty(entityObject, cname, date, 
/* 1148 */                     true);
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 1154 */               value = rs.getString(cindex);
/*      */               
/* 1156 */               this.beanUtil.setBeanProperty(entityObject, cname, value, 
/* 1157 */                   true);
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/* 1164 */         resultList.add(entityObject);
/*      */       }
/*      */     
/* 1167 */     } catch (Exception e) {
/*      */       
/* 1169 */       e.printStackTrace();
/*      */       
/* 1171 */       return null;
/*      */     } 
/*      */     
/* 1174 */     return resultList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <T> ArrayList<T> resultSet2List(ResultSet rs, int offset, int rowNum, Class<T> persistenceClass) {
/* 1180 */     ArrayList<T> resultList = new ArrayList<>();
/*      */     
/*      */     try {
/* 1183 */       ResultSetMetaData rsMetaData = rs.getMetaData();
/*      */       
/* 1185 */       int columnCount = rsMetaData.getColumnCount();
/*      */       
/* 1187 */       for (int i = 0; i < offset; i++) {
/* 1188 */         rs.next();
/*      */       }
/*      */ 
/*      */       
/* 1192 */       while (rs.next() && rowNum > 0)
/*      */       {
/* 1194 */         T entityObject = persistenceClass.newInstance();
/*      */         
/* 1196 */         for (int cindex = 1; cindex <= columnCount; cindex++) {
/*      */           Blob blob; Clob clob; Date date;
/* 1198 */           String value, cname = rsMetaData.getColumnName(cindex);
/*      */           
/* 1200 */           switch (rsMetaData.getColumnType(cindex)) {
/*      */ 
/*      */             
/*      */             case 2004:
/* 1204 */               blob = rs.getBlob(cindex);
/*      */               
/* 1206 */               this.beanUtil.setBeanProperty(entityObject, cname, blob, 
/* 1207 */                   true);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 2005:
/* 1212 */               clob = rs.getClob(cindex);
/*      */               
/* 1214 */               this.beanUtil.setBeanProperty(entityObject, cname, clob, 
/* 1215 */                   true);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 91:
/*      */             case 92:
/*      */             case 93:
/* 1222 */               date = rs.getDate(cindex);
/*      */               
/* 1224 */               this.beanUtil.setBeanProperty(entityObject, cname, date, 
/* 1225 */                   true);
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 1230 */               value = rs.getString(cindex);
/*      */               
/* 1232 */               this.beanUtil.setBeanProperty(entityObject, cname, value, 
/* 1233 */                   true);
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/* 1240 */         resultList.add(entityObject);
/* 1241 */         rowNum--;
/*      */       }
/*      */     
/* 1244 */     } catch (Exception e) {
/*      */       
/* 1246 */       e.printStackTrace();
/*      */       
/* 1248 */       return null;
/*      */     } 
/*      */     
/* 1251 */     return resultList;
/*      */   }
/*      */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\jdbc\template\JDBCTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */