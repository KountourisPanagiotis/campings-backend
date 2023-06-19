package gr.aueb.cf.cfcampingsjax.service.util;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CorsFilter is a web filter class that implements the javax.servlet.Filter interface.
 * It provides Cross-Origin Resource Sharing (CORS) settings to enable web browsers to make
 * cross-origin HTTP requests securely.
 */
@WebFilter("/*")
public class CorsFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");

        chain.doFilter(request, response);
    }
}
