package com.chinasofti.ordersys.servlets.admin;

import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.UserInfo;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

































public class GetUserByPageServlet
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }











  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/xml");

    int page = Integer.parseInt(request.getParameter("page"));

    UserService service = new UserService();

    int maxPage = service.getMaxPage(10);

    page = (page < 1) ? 1 : page;

    page = (page > maxPage) ? maxPage : page;

    ArrayList<UserInfo> list = service.getByPage(page, 10);


    try {
      Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().newDocument();

      Element root = doc.createElement("users");

      doc.appendChild(root);

      for (UserInfo info : list) {

        Element user = doc.createElement("user");

        Element userId = doc.createElement("userId");

        userId.setTextContent((new StringBuilder(String.valueOf(info.getUserId()))).toString());

        user.appendChild(userId);

        Element userAccount = doc.createElement("userAccount");

        userAccount.setTextContent(info.getUserAccount());

        user.appendChild(userAccount);

        Element roleId = doc.createElement("roleId");

        roleId.setTextContent((new StringBuilder(String.valueOf(info.getRoleId()))).toString());

        user.appendChild(roleId);

        Element roleName = doc.createElement("roleName");

        roleName.setTextContent(info.getRoleName());

        user.appendChild(roleName);

        Element locked = doc.createElement("locked");

        locked.setTextContent((new StringBuilder(String.valueOf(info.getLocked()))).toString());

        user.appendChild(locked);

        Element faceimg = doc.createElement("faceimg");

        faceimg.setTextContent((new StringBuilder(String.valueOf(info.getFaceimg()))).toString());

        user.appendChild(faceimg);

        root.appendChild(user);
      }

      Element pageNow = doc.createElement("page");

      pageNow.setTextContent((new StringBuilder(String.valueOf(page))).toString());

      root.appendChild(pageNow);

      Element maxPageElement = doc.createElement("maxPage");

      maxPageElement.setTextContent((new StringBuilder(String.valueOf(maxPage))).toString());

      root.appendChild(maxPageElement);


      TransformerFactory.newInstance()
        .newTransformer()
        .transform(new DOMSource(doc),
          new StreamResult((OutputStream)response.getOutputStream()));
    }
    catch (Exception ex) {

      ex.printStackTrace();
    }
  }
}


