/*    */ package com.chinasofti.util.bean.cache;
/*    */ 
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import java.util.Hashtable;
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
/*    *//*
*//*    *//*
*//*    *//* public class FactoryBeanCache
*//*    *//* {
*//* 30 *//*   private Hashtable<String, ObjectFactoryCacheSoftReference> cache = new Hashtable<>();
*//*    *//*
*//*    *//*
*//*    *//*
*//* 34 *//*   ReferenceQueue queue = new ReferenceQueue();
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*   private void refershCache() {
*//* 41 *//*     ObjectFactoryCacheSoftReference ref = null;
*//*    *//*
*//* 43 *//*     while ((ref = (ObjectFactoryCacheSoftReference)this.queue.poll()) != null)
*//*    *//*     {
*//* 45 *//*       this.cache.remove(ref.key);
*//*    *//*     }
*//*    *//*   }
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*   public void putCacheBean(Object bean, String key) {
*//* 56 *//*     refershCache();
*//*    *//*
*//* 58 *//*     if (!this.cache.containsKey(key)) {
*//*    *//*
*//* 60 *//*       ObjectFactoryCacheSoftReference ref = new ObjectFactoryCacheSoftReference(
*//* 61 *//*           key, bean, this.queue);
*//* 62 *//*       System.out.println("将bean：" + bean + "加入缓存，key值为" + key);
*//*    *//*
*//* 64 *//*       bean = null;
*//*    *//*
*//* 66 *//*       this.cache.put(key, ref);
*//*    *//*     }
*//*    *//*   }
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*
*//*    *//*   public Object getCacheBean(String key) {
*//* 79 *//*     refershCache();
*//*    *//*
*//* 81 *//*     if (this.cache.containsKey(key)) {
*//*    *//*
*//* 83 *//*       ObjectFactoryCacheSoftReference ref = this.cache.get(key);
*//* 84 *//*       System.out.println("从缓存中获取名为" + key + "的bean");
*//*    *//*
*//* 86 *//*       return ref.get();
*//*    *//*     }
*//*    *//*
*//*    *//*
*//* 90 *//*     return null;
*//*    *//*   }
*//*    *//* }*/


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\bean\cache\FactoryBeanCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */