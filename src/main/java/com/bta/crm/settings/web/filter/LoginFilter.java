package com.bta.crm.settings.web.filter;

import com.bta.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Login过滤器建立");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String path=request.getServletPath();
        if("/login.html".equals(path)||"/settings/user/login.do".equals(path))
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            //已登录，放行
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            //重定向到登陆页面
            else {
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("Login过滤器销毁");
    }
}
