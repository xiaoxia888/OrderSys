package com.chinasofti.util.web.upload;

import org.apache.commons.io.IOUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        /*String savepath = "/img/faces";

        if (req.getParameter("path") != null)
         {
         savepath = req.getParameter("path");
        }*/
        //System.out.println("666");
        //System.out.println(path);

        File file=new File(path);
        //System.out.println("PATH:"+(path.compareTo("D:/store/img/dishes") > 0));
        //System.out.println("exit:"+file.exists());
        if(file.exists()){
            IOUtils.copy(new FileInputStream(file),resp.getOutputStream());
        }else {
            int i = path.lastIndexOf("/");
            String substring = path.substring(0, i);
            //System.out.println(substring);
            //默认图片
            String realPath="/img/faces/default.jpg";

            if (substring.equals("D:/store/img/dishes")){
                realPath = getServletContext().getRealPath("/img/dishes/1.jpg");
            }else {
                realPath = getServletContext().getRealPath("/img/faces/default.jpg");
            }
            //System.out.println(realPath);
            file=new File(realPath);
            IOUtils.copy(new FileInputStream(file),resp.getOutputStream());
        }
    }
}
