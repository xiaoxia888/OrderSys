/*     */ package com.chinasofti.util.bean;
/*     */ 
/*     */ import com.chinasofti.util.bean.convertor.BooleanConvertor;
/*     */ import com.chinasofti.util.bean.convertor.ByteConvertor;
/*     */ import com.chinasofti.util.bean.convertor.CharConvertor;
/*     */ import com.chinasofti.util.bean.convertor.DoubleConvertor;
/*     */ import com.chinasofti.util.bean.convertor.FloatConvertor;
/*     */ import com.chinasofti.util.bean.convertor.IntConvertor;
/*     */ import com.chinasofti.util.bean.convertor.LongConvertor;
/*     */ import com.chinasofti.util.bean.convertor.ShortConvertor;
/*     */ import com.chinasofti.util.bean.convertor.StringConvertor;
/*     */ import com.chinasofti.util.bean.convertor.TypeConvertor;
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Hashtable;
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
/*     */ public class BeanUtil
/*     */ {
/*     */   public Hashtable<String, TypeConvertor> getConvertors() {
/*  46 */     return this.convertors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConvertors(Hashtable<String, TypeConvertor> convertors) {
/*  54 */     this.convertors = convertors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private Hashtable<String, TypeConvertor> convertors = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BeanUtil() {
/*  68 */     this.convertors.put("java.lang.String", new StringConvertor());
/*  69 */     this.convertors.put("int", new IntConvertor());
/*  70 */     this.convertors.put("java.lang.Integer", new IntConvertor());
/*  71 */     this.convertors.put("byte", new ByteConvertor());
/*  72 */     this.convertors.put("java.lang.Byte", new ByteConvertor());
/*  73 */     this.convertors.put("short", new ShortConvertor());
/*  74 */     this.convertors.put("java.lang.Short", new ShortConvertor());
/*  75 */     this.convertors.put("long", new LongConvertor());
/*  76 */     this.convertors.put("java.lang.Long", new LongConvertor());
/*  77 */     this.convertors.put("float", new FloatConvertor());
/*  78 */     this.convertors.put("java.lang.Float", new FloatConvertor());
/*  79 */     this.convertors.put("double", new DoubleConvertor());
/*  80 */     this.convertors.put("java.lang.Double", new DoubleConvertor());
/*  81 */     this.convertors.put("boolean", new BooleanConvertor());
/*  82 */     this.convertors.put("java.lang.Boolean", new BooleanConvertor());
/*  83 */     this.convertors.put("char", new CharConvertor());
/*  84 */     this.convertors.put("java.lang.Character", new CharConvertor());
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
/*     */   public void setConvertor(String targetType, TypeConvertor convertor) {
/*  98 */     this.convertors.put(targetType, convertor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeConvertor(String targetType) {
/* 109 */     this.convertors.remove(targetType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearConvertor() {
/* 116 */     this.convertors.clear();
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
/*     */   
/*     */   public void setBeanProperty(Object bean, String propertyName, String propertyValue, boolean IgnoreCase) {
/* 134 */     PropertyDescriptor[] pds = getBeanPropertyDescriptor(bean); byte b; int i;
/*     */     PropertyDescriptor[] arrayOfPropertyDescriptor1;
/* 136 */     for (i = (arrayOfPropertyDescriptor1 = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[b];
/*     */       
/* 138 */       if ((!IgnoreCase && pd.getName().equals(propertyName)) || (
/* 139 */         IgnoreCase && pd.getName().equalsIgnoreCase(
/* 140 */           propertyName))) {
/*     */         
/* 142 */         Method setter = pd.getWriteMethod();
/*     */         
/* 144 */         String typeString = setter.getParameterTypes()[0]
/* 145 */           .getCanonicalName();
/* 146 */         Object value = propertyValue;
/*     */         
/* 148 */         if (this.convertors.get(typeString) != null)
/*     */         {
/* 150 */           value = ((TypeConvertor)this.convertors.get(typeString)).convertToObject(
/* 151 */               propertyValue);
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 156 */           setter.invoke(bean, new Object[] { value });
/* 157 */         } catch (Exception ex) {
/*     */           
/* 159 */           throw new FillBeanException(bean.getClass()
/* 160 */               .getCanonicalName(), propertyName);
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBeanProperty(Object bean, String propertyName, Object propertyValue, boolean IgnoreCase) {
/* 184 */     PropertyDescriptor[] pds = getBeanPropertyDescriptor(bean); byte b; int i;
/*     */     PropertyDescriptor[] arrayOfPropertyDescriptor1;
/* 186 */     for (i = (arrayOfPropertyDescriptor1 = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[b];
/*     */       
/* 188 */       if ((!IgnoreCase && pd.getName().equals(propertyName)) || (
/* 189 */         IgnoreCase && pd.getName().equalsIgnoreCase(
/* 190 */           propertyName))) {
/*     */         
/* 192 */         Method setter = pd.getWriteMethod();
/*     */         
/* 194 */         String typeString = setter.getParameterTypes()[0]
/* 195 */           .getCanonicalName();
/*     */         
/*     */         try {
/* 198 */           if (this.convertors.get(typeString) != null)
/*     */           {
/* 200 */             propertyValue = ((TypeConvertor)this.convertors.get(typeString))
/* 201 */               .convertToObject(propertyValue);
/*     */           }
/*     */           
/* 204 */           setter.invoke(bean, new Object[] { propertyValue });
/* 205 */         } catch (Exception ex) {
/*     */           
/* 207 */           throw new FillBeanException(bean.getClass()
/* 208 */               .getCanonicalName(), propertyName);
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PropertyDescriptor[] getBeanPropertyDescriptor(Object bean) {
/*     */     try {
/* 222 */       BeanInfo binfo = Introspector.getBeanInfo(bean.getClass());
/*     */       
/* 224 */       PropertyDescriptor[] pds = binfo.getPropertyDescriptors();
/*     */       
/* 226 */       return pds;
/* 227 */     } catch (Exception e) {
/*     */       
/* 229 */       e.printStackTrace();
/*     */       
/* 231 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\BeanUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */