package auth.service.impl;

import auth.constant.AuthConstant;
import auth.constant.InfoConstant;
import auth.dto.AuthDto;
import auth.entity.AuthUser;
import auth.exception.UserOperationException;
import auth.repository.UserRepository;
import auth.service.UserService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author fdse
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public AuthUser saveUser(AuthUser user) {
        return null;
    }

    @Override
    public List<AuthUser> getAllUser(HttpHeaders headers) {
        return userRepository.findAll();
    }

    private RestTemplate restTemplate = new RestTemplate();
    private static final String ADMIN_USER_SERVICE_URI = "http://ts-admin-user-service:16115/api/v1/adminuserservice/users";

    /**
     * create  a user with default role of user
     *
     * @param dto
     * @return
     */
    @Override
    public AuthUser createDefaultAuthUser(AuthDto dto) {
        LOGGER.info("Register User Info is:  " + dto.getUserName());
        AuthUser user = AuthUser.builder()
                .userId(UUID.fromString(dto.getUserId()))
                .username(dto.getUserName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(new HashSet<>(Arrays.asList(AuthConstant.ROLE_USER)))
                .build();

        checkUserCreateInfo(user);
        return userRepository.save(user);
    }

    @Override
    public Response deleteByUserId(UUID userId, HttpHeaders headers) {
        LOGGER.info("DELETE USER :" + userId);
        userRepository.deleteByUserId(userId);
        return new Response(1, "DELETE USER SUCCESS", null);
    }

    @Override
    public Response adminUserWelcome(HttpHeaders headers) {
        HttpEntity<Response> httpEntity = new HttpEntity<>(headers);
        restTemplate.exchange(ADMIN_USER_SERVICE_URI + "/welcome",
                HttpMethod.GET,
                httpEntity,
                Response.class);
        return new Response<>(1, "ADMIN USER WELCOME SUCCESS", null);
    }

    /**
     * check Whether user info is empty
     *
     * @param user
     */
    private void checkUserCreateInfo(AuthUser user) {
        List<String> infos = new ArrayList<>();

        if (null == user.getUsername() || "".equals(user.getUsername())) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY_1, InfoConstant.USERNAME));
        }

        int passwordMaxLength = 6;
        if (null == user.getPassword()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY_1, InfoConstant.PASSWORD));
        } else if (user.getPassword().length() < passwordMaxLength) {
            infos.add(MessageFormat.format(InfoConstant.PASSWORD_LEAST_CHAR_1, 6));
        }

        if (null == user.getRoles() || user.getRoles().isEmpty()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY_1, InfoConstant.ROLES));
        }

        if (!infos.isEmpty()) {
            LOGGER.error(infos.toString());
            throw new UserOperationException(infos.toString());
        }
    }

}
