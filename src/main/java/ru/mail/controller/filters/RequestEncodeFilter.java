package ru.mail.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Denis Monich
 * this filter setting encoding type for request and response (UTF-8)
 */
public class RequestEncodeFilter implements Filter {

    private static final Logger logger = Logger.getLogger(RequestEncodeFilter.class);

    private static final String ENCODING_TYPE = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
        //Default
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("RequestEncode filter  is working");
        //setting request character encoding
        request.setCharacterEncoding(ENCODING_TYPE);
        chain.doFilter(request, response);
        //setting response content type
        response.setContentType(CONTENT_TYPE);
    }

    public void destroy() {
        //Default
    }
}