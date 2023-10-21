package yukiii.mangayukiii.adapter.auth.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yukiii.mangayukiii.adapter.auth.SecurityContextService;
import yukiii.mangayukiii.constant.AuthenticationClaimer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SecurityContextServiceImpl implements SecurityContextService {

  @Value("${setting.service.internal.key}")
  private String internalKey;

  @Value("${setting.service.internal.name}")
  private String internalName;

  @Override
  public AbstractAuthenticationToken decodeApiSecret(String key) {
    AbstractAuthenticationToken userToken = null;

    if (StringUtils.hasText(key) && key.equals(internalKey)){

      userToken = new RunAsUserToken(
        key,
        internalName,
        internalName,
        Arrays.stream(AuthenticationClaimer.values()).map(x ->
          new SimpleGrantedAuthority(x.toString()))
          .collect(Collectors.toList()), null
      );
    }

    return userToken;
  }
}