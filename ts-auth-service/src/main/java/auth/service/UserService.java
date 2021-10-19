package auth.service;

import auth.dto.AuthDto;
import auth.entity.AuthUser;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.UUID;

/**
 * @author fdse
 */
public interface UserService {

    /**
     * save user
     *
     * @param user user
     * @return user
     */
    AuthUser saveUser(AuthUser user);

    /**
     * get all users
     *
     * @param headers headers
     * @return List<User>
     */
    List<AuthUser> getAllUser(HttpHeaders headers);

    /**
     * create default auth user
     *
     * @param dto dto
     * @return user
     */
    AuthUser createDefaultAuthUser(AuthDto dto);

    /**
     * delete by user id
     *
     * @param userId user id
     * @param headers headers
     * @return Response
     */
    Response deleteByUserId(UUID userId, HttpHeaders headers);

    Response adminUserWelcome(HttpHeaders headers);

}
