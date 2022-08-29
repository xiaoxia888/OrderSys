package com.chinasofti.ordersys.filters;

import com.chinasofti.ordersys.vo.UserInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;










public class WaiterFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest)arg0;

      HttpServletResponse response = (HttpServletResponse)arg1;

      HttpSession session = request.getSession();

      if (session.getAttribute("USER_INFO") != null && (
            (UserInfo)session.getAttribute("USER_INFO")).getRoleId() == 3) {

        arg2.doFilter(arg0, arg1);
      }
      else {

        response.sendRedirect("/OrderSys");
      }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

