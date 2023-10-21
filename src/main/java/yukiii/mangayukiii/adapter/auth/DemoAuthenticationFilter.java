package yukiii.mangayukiii.adapter.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yukiii.mangayukiii.constant.AppConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DemoAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private SecurityContextService authService;

  @Autowired
  private Environment environment;

  public DemoAuthenticationFilter(SecurityContextService authService) {
    this.authService = authService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

    String tokenHeader = request.getHeader(AppConstant.HEADER_SECURITY_API_AUTHORIZATION);
    String apiSecretHeader = request.getHeader(AppConstant.HEADER_SECURITY_API_SECRET);
    
    if (apiSecretHeader != null){

      SecurityContextHolder
              .getContext()
              .setAuthentication(authService.decodeApiSecret(apiSecretHeader));
    }

    filterChain.doFilter(request, response);
  }
}
