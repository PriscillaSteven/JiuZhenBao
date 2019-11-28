package com.mall.jiuzhenbao.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cross Origin Resource Sharing Filter
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {


    /**
     * This method will add CORS settings in response.
     * Ignoring options request.
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,HEAD,POST,PUT,DELETE,TRACE,CONNECT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //Wild card (*) value for this header has browser compatibility issues, hence replacing below code, with actual header values.
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Accept, Accept-Language, Content-Language, Content-Type");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     *  Just override this method and provide blank body
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Just override this method and provide blank body
     */
    @Override
    public void destroy() {
    }

}