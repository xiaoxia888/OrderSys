/*    */ package com.chinasofti.ordersys.service;

import com.chinasofti.web.common.httpequest.HttpRequestContext;
import javax.servlet.http.HttpServletRequest;




public class DomainProtectedService {
  public boolean isFromSameDomain() {
    HttpServletRequest request = HttpRequestContext.getRequest();

    String path = request.getContextPath();
    //System.out.println(path);

    String basePath = String.valueOf(request.getScheme()) + "://" + request.getServerName() +
      ":" + request.getServerPort() + path + "/";

    String fromUrl = request.getHeader("referer");

    return (fromUrl != null && fromUrl.startsWith(basePath));
  }
}

