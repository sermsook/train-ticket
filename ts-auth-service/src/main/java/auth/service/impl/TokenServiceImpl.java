package auth.service.impl;

import auth.constant.InfoConstant;
import auth.dto.BasicAuthDto;
import auth.dto.TokenDto;
import auth.entity.AuthUser;
import auth.exception.UserOperationException;
import auth.repository.UserRepository;
import auth.security.jwt.JWTProvider;
import auth.service.TokenService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * @author fdse
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response getToken(BasicAuthDto dto, HttpHeaders headers) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String verifyCode = dto.getVerificationCode();
        LOGGER.info("LOGIN USER :" + username + " __ " + password + " __ " + verifyCode);

        if (!StringUtils.isEmpty(verifyCode)) {
            HttpEntity requestEntity = new HttpEntity(headers);
            ResponseEntity<Boolean> re = restTemplate.exchange(
                    "http://ts-verification-code-service:15678/api/v1/verifycode/verify/" + verifyCode,
                    HttpMethod.GET,
                    requestEntity,
                    Boolean.class);
            boolean id = re.getBody();

            // failed code
            if (!id) {
                return new Response<>(0, "Verification failed.", null);
            }
        }

        // verify username and password
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(upat);
        } catch (AuthenticationException e) {
            return new Response<>(0, "Incorrect username or password.", null);
        }

        AuthUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserOperationException(MessageFormat.format(
                        InfoConstant.USER_NAME_NOT_FOUND_1, username
                )));
        String token = jwtProvider.createToken(user);
        LOGGER.info(token + "USER TOKEN");
        LOGGER.info(user.getUserId() + "   USER ID");
        return new Response<>(1, "login success", new TokenDto(user.getUserId(), username, token));
    }
}
