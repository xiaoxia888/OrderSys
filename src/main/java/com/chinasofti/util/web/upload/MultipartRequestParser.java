package com.chinasofti.util.web.upload;

import com.chinasofti.util.bean.BeanUtil;
import com.chinasofti.util.bean.convertor.TypeConvertor;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;










public class MultipartRequestParser
{
  private BeanUtil beanUtil = new BeanUtil();



  private Hashtable<String, String[]> txtParameters = (Hashtable)new Hashtable<>();



  private Hashtable<String, FormFile[]> fileParameters = (Hashtable)new Hashtable<>();












  public Object parse(HttpServletRequest request, Object bean) {
    try {
      publishProgress(request, 0);

      HttpSession session = request.getSession(true);

      Enumeration<String> parameterNames = request.getParameterNames();

      while (parameterNames.hasMoreElements()) {

        String parameterName = parameterNames.nextElement();

        String[] values = request.getParameterValues(parameterName);

        this.txtParameters.put(parameterName, values);
      }

      String contentType = request.getContentType();

      if ("post".equalsIgnoreCase(request.getMethod()) &&
        contentType.toLowerCase().startsWith(
          "multipart/form-data;")) {


        String boundary = contentType.substring(contentType
            .indexOf("boundary=") + 9);

        ServletInputStream sis = request.getInputStream();

        int length = request.getContentLength();

        session.setAttribute("ICSS_UTIL_WEB_UPLOAD_STATE",
            "ICSS_UTIL_WEB_UPLOAD_UPLOADING");

        byte[] buf = new byte[4096];

        int c = 0;

        int readCounter = 0;

        while ((c = sis.readLine(buf, 0, buf.length)) != -1) {

          readCounter += c;

          String msg = new String(buf, 0, c, "utf-8");

          if (msg.indexOf("filename=\"") != -1) {

            FormFile fileParameter = new FormFile();

            String fileName = "";

            fileName = msg
              .substring(msg.indexOf("filename=\"") + 10);
            fileName = fileName
              .substring(0, fileName.indexOf("\""));
            fileName = fileName.substring(fileName
                .lastIndexOf("\\") + 1);


            fileParameter.setFileName(fileName);

            String name = msg.substring(msg.indexOf("name=\"") + 6);
            name = name.substring(0, name.indexOf("\""));

            c = sis.readLine(buf, 0, buf.length);

            readCounter += c;

            String s = new String(buf, 0, c);

            String contextType = s.substring(s.indexOf(":") + 1)
              .trim();

            fileParameter.setContextType(contextType);

            c = sis.readLine(buf, 0, buf.length);

            readCounter += c;

            String path = request.getSession().getServletContext()
              .getRealPath("/WEB-INF/temp");



            File testPath = new File(path);
            if (!testPath.exists()) {
              testPath.mkdir();
            }




            File tempFile = File.createTempFile("httpupload",
                ".uploadtemp", new File(path));

            FileOutputStream fos = new FileOutputStream(tempFile);

            while ((c = sis.readLine(buf, 0, buf.length)) != -1) {

              readCounter += c;

              publishProgress(request, length, readCounter);

              msg = new String(buf, 0, c);

              if (msg.trim().equals("--" + boundary) ||
                msg.trim()
                .equals("--" + boundary + "--")) {
                break;
              }

              fos.write(buf, 0, c);
            }

            fos.flush();

            fos.close();

            fileParameter.setTempFile(tempFile);

            addFileParameter(name, fileParameter); continue;
          }
          if (msg.indexOf("name=\"") != -1) {

            String name = msg.substring(msg.indexOf("name=\"") + 6);
            name = name.substring(0, name.indexOf("\""));

            c = sis.readLine(buf, 0, buf.length);

            readCounter += c;

            String value = "";

            while ((c = sis.readLine(buf, 0, buf.length)) != -1) {

              readCounter += c;

              String s = new String(buf, 0, c, "utf-8");

              if (s.trim().equals("--" + boundary) ||
                s.trim().equals("--" + boundary + "--")) {
                break;
              }

              value = String.valueOf(value) + s;
            }

            byte[] valueByte = value.getBytes();

            addTxtParameter(name, new String(valueByte, 0,
                  valueByte.length - 2));

            publishProgress(request, length, readCounter); continue;
          }
          if (msg.trim().equals("--" + boundary + "--")) {
            break;
          }
        }

        publishProgress(request, 100);
      }


      fillBean(bean);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return bean;
  }









  private void fillBean(Object bean) {
    try {
      BeanInfo info = Introspector.getBeanInfo(bean.getClass());

      PropertyDescriptor[] pds = info.getPropertyDescriptors();

      Enumeration<String> txtKeys = this.txtParameters.keys();

      while (txtKeys.hasMoreElements()) {

        String key = txtKeys.nextElement();

        String[] values = this.txtParameters.get(key); byte b; int i;
        PropertyDescriptor[] arrayOfPropertyDescriptor;
        for (i = (arrayOfPropertyDescriptor = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor[b];

          if (pd.getName().equals(key)) {

            Method setMethod = pd.getWriteMethod();

            Class<?> argType = setMethod.getParameterTypes()[0];

            if (!argType.isArray()) {

              String value = values[0];

              Object objValue = value;

              TypeConvertor convertor = (TypeConvertor)this.beanUtil.getConvertors()
                .get(argType.getCanonicalName());

              if (convertor != null) {
                objValue = convertor.convertToObject(value);
              }

              setMethod.invoke(bean, new Object[] { objValue });
            } else {

              Object[] objValues = new Object[values.length];

              Class<?> elementType = argType.getComponentType();

              TypeConvertor convertor = (TypeConvertor)this.beanUtil.getConvertors()
                .get(elementType.getCanonicalName());

              for (int j = 0; j < objValues.length; j++) {

                if (convertor != null) {
                  objValues[j] = convertor
                    .convertToObject(values[j]);
                } else {

                  objValues[j] = values[j];
                }
              }

              setMethod.invoke(bean, new Object[] { objValues });
            }
          }
          b++; }

      }
      Enumeration<String> fileKeys = this.fileParameters.keys();

      while (fileKeys.hasMoreElements()) {

        String key = fileKeys.nextElement();

        FormFile[] files = this.fileParameters.get(key); byte b; int i;
        PropertyDescriptor[] arrayOfPropertyDescriptor;
        for (i = (arrayOfPropertyDescriptor = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor[b];

          if (pd.getName().equals(key)) {

            Method setMethod = pd.getWriteMethod();

            Class<?> argType = setMethod.getParameterTypes()[0];

            if (argType.isArray()) {
              setMethod.invoke(bean, new Object[] { files });
            } else {

              setMethod.invoke(bean, new Object[] { files[0] });
            }
          }
          b++; }

      }
    } catch (Exception ex) {

      ex.printStackTrace();
    }
  }












  private void addTxtParameter(String pname, String pvalue) {
    if (this.txtParameters.containsKey(pname)) {

      String[] values = this.txtParameters.get(pname);

      String[] newValues = Arrays.<String>copyOf(values, values.length + 1);

      newValues[values.length] = pvalue;

      this.txtParameters.put(pname, newValues);
    } else {

      this.txtParameters.put(pname, new String[] { pvalue });
    }
  }











  private void addFileParameter(String pname, FormFile file) {
    if (this.fileParameters.containsKey(pname)) {

      FormFile[] values = this.fileParameters.get(pname);

      FormFile[] newValues = Arrays.<FormFile>copyOf(values, values.length + 1);

      newValues[values.length] = file;

      this.fileParameters.put(pname, newValues);
    } else {

      this.fileParameters.put(pname, new FormFile[] { file });
    }
  }











  private Integer getProgress(int contentLength, int readCounter) {
    return new Integer((int)(readCounter * 100L / contentLength));
  }













  private void publishProgress(HttpServletRequest request, int contentLength, int readCounter) {
    request.getSession(true).setAttribute("ICSS_UTIL_WEB_UPLOAD_PROGRESS",
        getProgress(contentLength, readCounter));
  }










  private void publishProgress(HttpServletRequest request, int progress) {
    request.getSession(true).setAttribute("ICSS_UTIL_WEB_UPLOAD_PROGRESS",
        new Integer(progress));
  }













  public Object parse(HttpServletRequest request, Class beanClass) {
    try {
      Object bean = beanClass.newInstance();

      return parse(request, bean);
    } catch (Exception e) {

      e.printStackTrace();

      return null;
    }
  }














  public Object parse(HttpServletRequest request, String beanClassName) {
    try {
      Class<?> cls = Class.forName(beanClassName);

      return parse(request, cls);
    } catch (Exception e) {

      e.printStackTrace();

      return null;
    }
  }











  public static int getUploadProgress(HttpServletRequest request) {
    try {
      return ((Integer)request.getSession(true).getAttribute(
          "ICSS_UTIL_WEB_UPLOAD_PROGRESS")).intValue();
    } catch (Exception e) {

      return 0;
    }
  }
}

