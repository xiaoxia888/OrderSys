/*     */ package com.chinasofti.web.common.service;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Random;
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
/*     */ public class SaveCodeService
/*     */ {
/*     */   private char[] elements;
/*     */   private int width;
/*     */   private int height;
/*     */   private int length;
/*     */   private BufferedImage image;
/*     */   private String codeString;
/*     */   
/*     */   public SaveCodeService(char[] elements, int width, int height, int length) {
/*  71 */     this.elements = elements;
/*     */     
/*  73 */     this.width = width;
/*     */     
/*  75 */     this.height = height;
/*     */     
/*  77 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSaveCodeImage() {
/*  85 */     this.image = new BufferedImage(this.width, this.height, 1);
/*     */     
/*  87 */     Graphics g = this.image.getGraphics();
/*     */     
/*  89 */     Random random = new Random();
/*     */     
/*  91 */     g.setColor(getRandColor(220, 250));
/*     */     
/*  93 */     g.fillRect(0, 0, this.width, this.height);
/*     */     
/*  95 */     g.setFont(new Font("Times New Roman", 0, 18));
/*     */ 
/*     */     
/*  98 */     g.draw3DRect(0, 0, this.width - 1, this.height - 1, true);
/*     */     
/* 100 */     g.setColor(getRandColor(160, 200));
/*     */     int i;
/* 102 */     for (i = 0; i < 155; i++) {
/*     */       
/* 104 */       int x = random.nextInt(this.width);
/*     */       
/* 106 */       int y = random.nextInt(this.height);
/*     */       
/* 108 */       int xl = random.nextInt(12);
/*     */       
/* 110 */       int yl = random.nextInt(12);
/*     */       
/* 112 */       g.drawLine(x, y, x + xl, y + yl);
/*     */     } 
/*     */     
/* 115 */     this.codeString = "";
/*     */     
/* 117 */     for (i = 0; i < this.length; i++) {
/*     */       
/* 119 */       char rand = this.elements[Math.abs(random.nextInt()) % this.elements.length];
/*     */       
/* 121 */       this.codeString = String.valueOf(this.codeString) + rand;
/*     */       
/* 123 */       g.setColor(new Color(20 + random.nextInt(110), 20 + random
/* 124 */             .nextInt(110), 20 + random.nextInt(110)));
/*     */       
/* 126 */       g.drawString(String.valueOf(rand), 13 * i + 6, 16);
/*     */     } 
/*     */ 
/*     */     
/* 130 */     g.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() {
/* 141 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCodeString() {
/* 151 */     return this.codeString;
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
/*     */   private Color getRandColor(int fc, int bc) {
/* 165 */     Random random = new Random();
/*     */     
/* 167 */     if (fc > 255)
/*     */     {
/* 169 */       fc = 255;
/*     */     }
/* 171 */     if (bc > 255)
/*     */     {
/* 173 */       bc = 255;
/*     */     }
/* 175 */     int r = fc + random.nextInt(bc - fc);
/*     */     
/* 177 */     int g = fc + random.nextInt(bc - fc);
/*     */     
/* 179 */     int b = fc + random.nextInt(bc - fc);
/*     */     
/* 181 */     return new Color(r, g, b);
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasofti\web\common\service\SaveCodeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */