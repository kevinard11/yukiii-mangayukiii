package yukiii.mangayukiii.adapter.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface SecurityContextService {

  AbstractAuthenticationToken decodeApiSecret(String key);
}