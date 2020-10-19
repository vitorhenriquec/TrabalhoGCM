package br.ufrn.imd.projetobancario.BancoGCM.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    private static final String[] ALLOWED_METHODS = { "GET", "OPTIONS", "POST", "PATCH", "DELETE" };
    private static final String[] ALLOWED_HEADERS = { "Accept", "Referer", "User-Agent", "Authorization", "X-Organizacao-ID",
            "Organizacao-Id", "Origin", "X-Requested-With", "Content-Type", "Accept", "Sec-Fetch-Mode", "x-ijt",
            "Content-Type" };

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Vary", "Origin");
        response.setHeader("Access-Control-Allow-Methods", String.join(", ", ALLOWED_METHODS));
        response.setHeader("Access-Control-Allow-Headers", String.join(", ", ALLOWED_HEADERS));
        response.setHeader("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        // Not implemented
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Not implemented
    }
}
