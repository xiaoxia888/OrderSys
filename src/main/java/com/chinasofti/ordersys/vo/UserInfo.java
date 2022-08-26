/*     */ package com.chinasofti.ordersys.vo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserInfo
/*     */ {
/*     */   private int userId;
/*     */   private String userAccount;
/*     */   private String userPass;
/*     */   private int roleId;
/*     */   private String roleName;
/*     */   private int locked;
/*  51 */   private String faceimg = "default.jpg";
/*     */   
/*     */   public String getFaceimg() {
/*  54 */     return this.faceimg;
/*     */   }
/*     */   
/*     */   public void setFaceimg(String faceimg) {
/*  58 */     this.faceimg = faceimg;
/*     */   }
/*     */   
/*     */   public int getLocked() {
/*  62 */     return this.locked;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocked(int locked) {
/*  67 */     this.locked = locked;
/*     */   }
/*     */   
/*     */   public int getUserId() {
/*  71 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(int userId) {
/*  75 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public String getUserAccount() {
/*  79 */     return this.userAccount;
/*     */   }
/*     */   
/*     */   public void setUserAccount(String userAccount) {
/*  83 */     this.userAccount = userAccount;
/*     */   }
/*     */   
/*     */   public String getUserPass() {
/*  87 */     return this.userPass;
/*     */   }
/*     */   
/*     */   public void setUserPass(String userPass) {
/*  91 */     this.userPass = userPass;
/*     */   }
/*     */   
/*     */   public int getRoleId() {
/*  95 */     return this.roleId;
/*     */   }
/*     */   
/*     */   public void setRoleId(int roleId) {
/*  99 */     this.roleId = roleId;
/*     */   }
/*     */   
/*     */   public String getRoleName() {
/* 103 */     return this.roleName;
/*     */   }
/*     */   
/*     */   public void setRoleName(String roleName) {
/* 107 */     this.roleName = roleName;
/*     */   }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", locked=" + locked +
                ", faceimg='" + faceimg + '\'' +
                '}';
    }

    /*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\ordersys\vo\UserInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */