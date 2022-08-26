/*     */ package com.chinasofti.util.sec;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.Base64;
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
/*     */ public class Passport
/*     */ {
/*     */   public static void main(String[] args) {
/*  42 */     Passport passport = new Passport();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     System.out.println(passport.md5("admin"));
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
/*     */   public String md5(String x) {
/*  64 */     MessageDigest m = null;
/*     */     
/*     */     try {
/*  67 */       m = MessageDigest.getInstance("MD5");
/*     */       
/*  69 */       m.update(x.getBytes("UTF8"));
/*     */     }
/*  71 */     catch (NoSuchAlgorithmException e) {
/*     */       
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     catch (UnsupportedEncodingException e) {
/*     */       
/*  77 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  80 */     byte[] s = m.digest();
/*     */ 
/*     */     
/*  83 */     StringBuilder result = new StringBuilder("");
/*     */     
/*  85 */     for (int i = 0; i < s.length; i++)
/*     */     {
/*  87 */       result.append(Integer.toHexString(0xFF & s[i] | 0xFFFFFF00)
/*  88 */           .substring(6));
/*     */     }
/*     */     
/*  91 */     return result.toString();
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
/*     */   public String base64_decode(String txt) {
/* 106 */     Base64.Decoder base64_decode = Base64.getDecoder();
/*     */     
/* 108 */     String str = "";
/*     */     
/* 110 */     str = new String(base64_decode.decode(txt));
/*     */     
/* 112 */     return str;
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
/*     */   public String base64_encode(String txt) {
/* 124 */     Base64.Encoder base64_encode = Base64.getEncoder();
/*     */     
/* 126 */     return base64_encode.encodeToString(txt.getBytes());
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
/*     */   public String passport_encrypt(String txt, String key) {
/* 141 */     Random random = new Random();
/*     */     
/* 143 */     String rad = String.valueOf(random.nextInt(32000));
/*     */     
/* 145 */     String encrypt_key = md5(rad);
/*     */ 
/*     */     
/* 148 */     int ctr = 0;
/*     */     
/* 150 */     StringBuilder tmp = new StringBuilder("");
/*     */ 
/*     */     
/* 153 */     char[] encrypt_key_char = encrypt_key.toCharArray();
/*     */     
/* 155 */     char[] txt_char = txt.toCharArray();
/*     */     
/* 157 */     for (int i = 0; i < txt.length(); i++) {
/*     */       
/* 159 */       ctr = (ctr == encrypt_key_char.length) ? 0 : ctr;
/*     */ 
/*     */       
/* 162 */       char tmp1 = txt_char[i];
/*     */       
/* 164 */       char tmp4 = encrypt_key_char[ctr];
/*     */       
/* 166 */       char tmp2 = encrypt_key_char[ctr++];
/*     */       
/* 168 */       char tmp3 = (char)(tmp1 ^ tmp2);
/*     */       
/* 170 */       tmp.append(String.valueOf(tmp4) + tmp3);
/*     */     } 
/*     */     
/* 173 */     return base64_encode(passport_key(tmp.toString(), key));
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
/*     */   public String passport_decrypt(String txt, String key) {
/* 191 */     txt = passport_key(base64_decode(txt), key);
/*     */     
/* 193 */     StringBuilder tmp = new StringBuilder("");
/*     */     
/* 195 */     char[] txt_char = txt.toCharArray();
/*     */     
/* 197 */     for (int i = 0; i < txt.length(); i++)
/*     */     {
/*     */       
/* 200 */       tmp.append((char)(txt_char[i] ^ txt_char[++i]));
/*     */     }
/*     */ 
/*     */     
/* 204 */     return tmp.toString();
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
/*     */   String passport_key(String txt, String encrypt_key) {
/* 221 */     encrypt_key = md5(encrypt_key);
/*     */     
/* 223 */     int ctr = 0;
/*     */     
/* 225 */     StringBuilder tmp = new StringBuilder("");
/*     */ 
/*     */     
/* 228 */     char[] encrypt_key_char = encrypt_key.toCharArray();
/*     */     
/* 230 */     char[] txt_char = txt.toCharArray();
/*     */     
/* 232 */     for (int i = 0; i < txt.length(); i++) {
/*     */       
/* 234 */       ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
/*     */ 
/*     */       
/* 237 */       char c = (char)(txt_char[i] ^ encrypt_key_char[ctr++]);
/*     */       
/* 239 */       tmp.append(c);
/*     */     } 
/*     */ 
/*     */     
/* 243 */     return tmp.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\xgx\Desktop\点餐系统\apache-tomcat-9.0.64\webapps\OrderSys\WEB-INF\classes\!\com\chinasoft\\util\sec\Passport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */