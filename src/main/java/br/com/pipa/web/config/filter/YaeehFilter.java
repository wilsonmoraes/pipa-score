package br.com.pipa.web.config.filter;

import br.com.pipa.common.exception.UnauthorizedAcessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static br.com.pipa.PipaScoreApp.JWT_SECRET;

public class YaeehFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;

        validateToken(request);
        chain.doFilter(req, res);
    }

    /**
     * Método criado pra validar se a requisição atual possui um token e se este
     * token é válido.
     *
     * @param request
     */
    protected void validateToken(final HttpServletRequest request) {
        YaeehFilter.parseToken(request.getHeader("Authorization"));
    }

    public static Claims parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return body;
        } catch (Exception e) {
            throw new UnauthorizedAcessException("Sua sessão expirou ou você não tem permissão para acessar esta área." +
                    " Realize o login e tente novamente");

        }
    }
}