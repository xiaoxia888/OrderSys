/*     */ package com.chinasofti.util.web.upload;
/*     */ 
/*     */ import com.chinasofti.util.bean.BeanUtil;
/*     */ import com.chinasofti.util.bean.convertor.TypeConvertor;
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.servlet.ServletInputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class MultipartRequestParser
/*     */ {
/*  46 */   private BeanUtil beanUtil = new BeanUtil();
/*     */ 
/*     */ 
/*     */   
/*  50 */   private Hashtable<String, String[]> txtParameters = (Hashtable)new Hashtable<>();
/*     */ 
/*     */ 
/*     */   
/*  54 */   private Hashtable<String, FormFile[]> fileParameters = (Hashtable)new Hashtable<>();
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
/*     */   public Object parse(HttpServletRequest request, Object bean) {
/*     */     try {
/*  69 */       publishProgress(request, 0);
/*     */       
/*  71 */       HttpSession session = request.getSession(true);
/*     */       
/*  73 */       Enumeration<String> parameterNames = request.getParameterNames();
/*     */       
/*  75 */       while (parameterNames.hasMoreElements()) {
/*     */         
/*  77 */         String parameterName = parameterNames.nextElement();
/*     */         
/*  79 */         String[] values = request.getParameterValues(parameterName);
/*     */         
/*  81 */         this.txtParameters.put(parameterName, values);
/*     */       } 
/*     */       
/*  84 */       String contentType = request.getContentType();
/*     */       
/*  86 */       if ("post".equalsIgnoreCase(request.getMethod()) && 
/*  87 */         contentType.toLowerCase().startsWith(
/*  88 */           "multipart/form-data;")) {
/*     */ 
/*     */         
/*  91 */         String boundary = contentType.substring(contentType
/*  92 */             .indexOf("boundary=") + 9);
/*     */         
/*  94 */         ServletInputStream sis = request.getInputStream();
/*     */         
/*  96 */         int length = request.getContentLength();
/*     */         
/*  98 */         session.setAttribute("ICSS_UTIL_WEB_UPLOAD_STATE", 
/*  99 */             "ICSS_UTIL_WEB_UPLOAD_UPLOADING");
/*     */         
/* 101 */         byte[] buf = new byte[4096];
/*     */         
/* 103 */         int c = 0;
/*     */         
/* 105 */         int readCounter = 0;
/*     */         
/* 107 */         while ((c = sis.readLine(buf, 0, buf.length)) != -1) {
/*     */           
/* 109 */           readCounter += c;
/*     */           
/* 111 */           String msg = new String(buf, 0, c, "utf-8");
/*     */           
/* 113 */           if (msg.indexOf("filename=\"") != -1) {
/*     */             
/* 115 */             FormFile fileParameter = new FormFile();
/*     */             
/* 117 */             String fileName = "";
/*     */             
/* 119 */             fileName = msg
/* 120 */               .substring(msg.indexOf("filename=\"") + 10);
/* 121 */             fileName = fileName
/* 122 */               .substring(0, fileName.indexOf("\""));
/* 123 */             fileName = fileName.substring(fileName
/* 124 */                 .lastIndexOf("\\") + 1);
/*     */ 
/*     */             
/* 127 */             fileParameter.setFileName(fileName);
/*     */             
/* 129 */             String name = msg.substring(msg.indexOf("name=\"") + 6);
/* 130 */             name = name.substring(0, name.indexOf("\""));
/*     */             
/* 132 */             c = sis.readLine(buf, 0, buf.length);
/*     */             
/* 134 */             readCounter += c;
/*     */             
/* 136 */             String s = new String(buf, 0, c);
/*     */             
/* 138 */             String contextType = s.substring(s.indexOf(":") + 1)
/* 139 */               .trim();
/*     */             
/* 141 */             fileParameter.setContextType(contextType);
/*     */             
/* 143 */             c = sis.readLine(buf, 0, buf.length);
/*     */             
/* 145 */             readCounter += c;
/*     */             
/* 147 */             String path = request.getSession().getServletContext()
/* 148 */               .getRealPath("/WEB-INF/temp");
/*     */ 
/*     */ 
/*     */             
/* 152 */             File testPath = new File(path);
/* 153 */             if (!testPath.exists()) {
/* 154 */               testPath.mkdir();
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 160 */             File tempFile = File.createTempFile("httpupload", 
/* 161 */                 ".uploadtemp", new File(path));
/*     */             
/* 163 */             FileOutputStream fos = new FileOutputStream(tempFile);
/*     */             
/* 165 */             while ((c = sis.readLine(buf, 0, buf.length)) != -1) {
/*     */               
/* 167 */               readCounter += c;
/*     */               
/* 169 */               publishProgress(request, length, readCounter);
/*     */               
/* 171 */               msg = new String(buf, 0, c);
/*     */               
/* 173 */               if (msg.trim().equals("--" + boundary) || 
/* 174 */                 msg.trim()
/* 175 */                 .equals("--" + boundary + "--")) {
/*     */                 break;
/*     */               }
/*     */               
/* 179 */               fos.write(buf, 0, c);
/*     */             } 
/*     */             
/* 182 */             fos.flush();
/*     */             
/* 184 */             fos.close();
/*     */             
/* 186 */             fileParameter.setTempFile(tempFile);
/*     */             
/* 188 */             addFileParameter(name, fileParameter); continue;
/*     */           } 
/* 190 */           if (msg.indexOf("name=\"") != -1) {
/*     */             
/* 192 */             String name = msg.substring(msg.indexOf("name=\"") + 6);
/* 193 */             name = name.substring(0, name.indexOf("\""));
/*     */             
/* 195 */             c = sis.readLine(buf, 0, buf.length);
/*     */             
/* 197 */             readCounter += c;
/*     */             
/* 199 */             String value = "";
/*     */             
/* 201 */             while ((c = sis.readLine(buf, 0, buf.length)) != -1) {
/*     */               
/* 203 */               readCounter += c;
/*     */               
/* 205 */               String s = new String(buf, 0, c, "utf-8");
/*     */               
/* 207 */               if (s.trim().equals("--" + boundary) || 
/* 208 */                 s.trim().equals("--" + boundary + "--")) {
/*     */                 break;
/*     */               }
/*     */               
/* 212 */               value = String.valueOf(value) + s;
/*     */             } 
/*     */             
/* 215 */             byte[] valueByte = value.getBytes();
/*     */             
/* 217 */             addTxtParameter(name, new String(valueByte, 0, 
/* 218 */                   valueByte.length - 2));
/*     */             
/* 220 */             publishProgress(request, length, readCounter); continue;
/*     */           } 
/* 222 */           if (msg.trim().equals("--" + boundary + "--")) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 227 */         publishProgress(request, 100);
/*     */       } 
/*     */ 
/*     */       
/* 231 */       fillBean(bean);
/* 232 */     } catch (Exception e) {
/*     */       
/* 234 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 237 */     return bean;
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
/*     */   private void fillBean(Object bean) {
/*     */     try {
/* 250 */       BeanInfo info = Introspector.getBeanInfo(bean.getClass());
/*     */       
/* 252 */       PropertyDescriptor[] pds = info.getPropertyDescriptors();
/*     */       
/* 254 */       Enumeration<String> txtKeys = this.txtParameters.keys();
/*     */       
/* 256 */       while (txtKeys.hasMoreElements()) {
/*     */         
/* 258 */         String key = txtKeys.nextElement();
/*     */         
/* 260 */         String[] values = this.txtParameters.get(key); byte b; int i;
/*     */         PropertyDescriptor[] arrayOfPropertyDescriptor;
/* 262 */         for (i = (arrayOfPropertyDescriptor = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor[b];
/*     */           
/* 264 */           if (pd.getName().equals(key)) {
/*     */             
/* 266 */             Method setMethod = pd.getWriteMethod();
/*     */             
/* 268 */             Class<?> argType = setMethod.getParameterTypes()[0];
/*     */             
/* 270 */             if (!argType.isArray()) {
/*     */               
/* 272 */               String value = values[0];
/*     */               
/* 274 */               Object objValue = value;
/*     */               
/* 276 */               TypeConvertor convertor = (TypeConvertor)this.beanUtil.getConvertors()
/* 277 */                 .get(argType.getCanonicalName());
/*     */               
/* 279 */               if (convertor != null) {
/* 280 */                 objValue = convertor.convertToObject(value);
/*     */               }
/*     */               
/* 283 */               setMethod.invoke(bean, new Object[] { objValue });
/*     */             } else {
/*     */               
/* 286 */               Object[] objValues = new Object[values.length];
/*     */               
/* 288 */               Class<?> elementType = argType.getComponentType();
/*     */               
/* 290 */               TypeConvertor convertor = (TypeConvertor)this.beanUtil.getConvertors()
/* 291 */                 .get(elementType.getCanonicalName());
/*     */               
/* 293 */               for (int j = 0; j < objValues.length; j++) {
/*     */                 
/* 295 */                 if (convertor != null) {
/* 296 */                   objValues[j] = convertor
/* 297 */                     .convertToObject(values[j]);
/*     */                 } else {
/*     */                   
/* 300 */                   objValues[j] = values[j];
/*     */                 } 
/*     */               } 
/*     */               
/* 304 */               setMethod.invoke(bean, new Object[] { objValues });
/*     */             } 
/*     */           } 
/*     */           b++; }
/*     */       
/*     */       } 
/* 310 */       Enumeration<String> fileKeys = this.fileParameters.keys();
/*     */       
/* 312 */       while (fileKeys.hasMoreElements()) {
/*     */         
/* 314 */         String key = fileKeys.nextElement();
/*     */         
/* 316 */         FormFile[] files = this.fileParameters.get(key); byte b; int i;
/*     */         PropertyDescriptor[] arrayOfPropertyDescriptor;
/* 318 */         for (i = (arrayOfPropertyDescriptor = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor[b];
/*     */           
/* 320 */           if (pd.getName().equals(key)) {
/*     */             
/* 322 */             Method setMethod = pd.getWriteMethod();
/*     */             
/* 324 */             Class<?> argType = setMethod.getParameterTypes()[0];
/*     */             
/* 326 */             if (argType.isArray()) {
/* 327 */               setMethod.invoke(bean, new Object[] { files });
/*     */             } else {
/*     */               
/* 330 */               setMethod.invoke(bean, new Object[] { files[0] });
/*     */             } 
/*     */           } 
/*     */           b++; }
/*     */       
/*     */       } 
/* 336 */     } catch (Exception ex) {
/*     */       
/* 338 */       ex.printStackTrace();
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
/*     */   
/*     */   private void addTxtParameter(String pname, String pvalue) {
/* 354 */     if (this.txtParameters.containsKey(pname)) {
/*     */       
/* 356 */       String[] values = this.txtParameters.get(pname);
/*     */       
/* 358 */       String[] newValues = Arrays.<String>copyOf(values, values.length + 1);
/*     */       
/* 360 */       newValues[values.length] = pvalue;
/*     */       
/* 362 */       this.txtParameters.put(pname, newValues);
/*     */     } else {
/*     */       
/* 365 */       this.txtParameters.put(pname, new String[] { pvalue });
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
/*     */   private void addFileParameter(String pname, FormFile file) {
/* 380 */     if (this.fileParameters.containsKey(pname)) {
/*     */       
/* 382 */       FormFile[] values = this.fileParameters.get(pname);
/*     */       
/* 384 */       FormFile[] newValues = Arrays.<FormFile>copyOf(values, values.length + 1);
/*     */       
/* 386 */       newValues[values.length] = file;
/*     */       
/* 388 */       this.fileParameters.put(pname, newValues);
/*     */     } else {
/*     */       
/* 391 */       this.fileParameters.put(pname, new FormFile[] { file });
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
/*     */   private Integer getProgress(int contentLength, int readCounter) {
/* 406 */     return new Integer((int)(readCounter * 100L / contentLength));
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
/*     */ 
/*     */   
/*     */   private void publishProgress(HttpServletRequest request, int contentLength, int readCounter) {
/* 422 */     request.getSession(true).setAttribute("ICSS_UTIL_WEB_UPLOAD_PROGRESS", 
/* 423 */         getProgress(contentLength, readCounter));
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
/*     */   private void publishProgress(HttpServletRequest request, int progress) {
/* 436 */     request.getSession(true).setAttribute("ICSS_UTIL_WEB_UPLOAD_PROGRESS", 
/* 437 */         new Integer(progress));
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
/*     */ 
/*     */   
/*     */   public Object parse(HttpServletRequest request, Class beanClass) {
/*     */     try {
/* 454 */       Object bean = beanClass.newInstance();
/*     */       
/* 456 */       return parse(request, bean);
/* 457 */     } catch (Exception e) {
/*     */       
/* 459 */       e.printStackTrace();
/*     */       
/* 461 */       return null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object parse(HttpServletRequest request, String beanClassName) {
/*     */     try {
/* 480 */       Class<?> cls = Class.forName(beanClassName);
/*     */       
/* 482 */       return parse(request, cls);
/* 483 */     } catch (Exception e) {
/*     */       
/* 485 */       e.printStackTrace();
/*     */       
/* 487 */       return null;
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
/*     */   public static int getUploadProgress(HttpServletRequest request) {
/*     */     try {
/* 503 */       return ((Integer)request.getSession(true).getAttribute(
/* 504 */           "ICSS_UTIL_WEB_UPLOAD_PROGRESS")).intValue();
/* 505 */     } catch (Exception e) {
/*     */       
/* 507 */       return 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\we\\upload\MultipartRequestParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */