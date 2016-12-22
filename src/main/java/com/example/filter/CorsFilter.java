package com.example.filter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kimyongyeon on 2016-12-22.
 */
public class CorsFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException
    {
        // 필터적용
        ((HttpServletResponse)res).setHeader("HelloHeader", "test");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void init(FilterConfig fc) throws ServletException
    {
    }
}
