package com.sdg.security.demo.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter Init......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //log.info("In MyFilter before Filter......");
        filterChain.doFilter(servletRequest,servletResponse);

        //log.info("In MyFilter after Filter......");


    }

    @Override
    public void destroy() {
        log.info("MyFilter Destory......");
    }
}
