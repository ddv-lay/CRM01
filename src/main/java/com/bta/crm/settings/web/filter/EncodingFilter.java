package com.bta.crm.settings.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Encoding过滤器创建");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入过滤器");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("Encoding过滤器销毁");
    }
}
