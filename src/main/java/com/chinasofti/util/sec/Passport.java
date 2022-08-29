package com.chinasofti.util.sec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;































public class Passport
{
  public static void main(String[] args) {
    Passport passport = new Passport();







    System.out.println(passport.md5("admin"));
  }











  public String md5(String x) {
    MessageDigest m = null;

    try {
      m = MessageDigest.getInstance("MD5");

      m.update(x.getBytes("UTF8"));
    }
    catch (NoSuchAlgorithmException e) {

      e.printStackTrace();
    }
    catch (UnsupportedEncodingException e) {

      e.printStackTrace();
    }

    byte[] s = m.digest();


    StringBuilder result = new StringBuilder("");

    for (int i = 0; i < s.length; i++)
    {
      result.append(Integer.toHexString(0xFF & s[i] | 0xFFFFFF00)
          .substring(6));
    }

    return result.toString();
  }












  public String base64_decode(String txt) {
    Base64.Decoder base64_decode = Base64.getDecoder();

    String str = "";

    str = new String(base64_decode.decode(txt));

    return str;
  }









  public String base64_encode(String txt) {
    Base64.Encoder base64_encode = Base64.getEncoder();

    return base64_encode.encodeToString(txt.getBytes());
  }












  public String passport_encrypt(String txt, String key) {
    Random random = new Random();

    String rad = String.valueOf(random.nextInt(32000));

    String encrypt_key = md5(rad);


    int ctr = 0;

    StringBuilder tmp = new StringBuilder("");


    char[] encrypt_key_char = encrypt_key.toCharArray();

    char[] txt_char = txt.toCharArray();

    for (int i = 0; i < txt.length(); i++) {

      ctr = (ctr == encrypt_key_char.length) ? 0 : ctr;


      char tmp1 = txt_char[i];

      char tmp4 = encrypt_key_char[ctr];

      char tmp2 = encrypt_key_char[ctr++];

      char tmp3 = (char)(tmp1 ^ tmp2);

      tmp.append(String.valueOf(tmp4) + tmp3);
    }

    return base64_encode(passport_key(tmp.toString(), key));
  }















  public String passport_decrypt(String txt, String key) {
    txt = passport_key(base64_decode(txt), key);

    StringBuilder tmp = new StringBuilder("");

    char[] txt_char = txt.toCharArray();

    for (int i = 0; i < txt.length(); i++)
    {

      tmp.append((char)(txt_char[i] ^ txt_char[++i]));
    }


    return tmp.toString();
  }














  String passport_key(String txt, String encrypt_key) {
    encrypt_key = md5(encrypt_key);

    int ctr = 0;

    StringBuilder tmp = new StringBuilder("");


    char[] encrypt_key_char = encrypt_key.toCharArray();

    char[] txt_char = txt.toCharArray();

    for (int i = 0; i < txt.length(); i++) {

      ctr = (ctr == encrypt_key.length()) ? 0 : ctr;


      char c = (char)(txt_char[i] ^ encrypt_key_char[ctr++]);

      tmp.append(c);
    }


    return tmp.toString();
  }
}

