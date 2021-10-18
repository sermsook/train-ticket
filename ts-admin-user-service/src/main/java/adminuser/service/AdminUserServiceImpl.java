package adminuser.service;

import adminuser.config.AdminUserProperties;
import adminuser.dto.UserDto;
import adminuser.entity.*;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

/**
 * @author fdse
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AdminUserProperties adminUserProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserServiceImpl.class);
//    private static final String USER_SERVICE_IP_URI = "http://ts-user-service:12342/api/v1/userservice/users";


    @Override
    public Response getAllUsers(HttpHeaders headers) {
        AdminUserServiceImpl.LOGGER.info("[Admin User Service][Get All Users]");
        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<Response<List<User>>> re = restTemplate.exchange(
                adminUserProperties.getUrl(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<List<User>>>() {
                });

        return re.getBody();
    }


    @Override
    public Response deleteUser(String userId, HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                adminUserProperties.getUrl() + "/" + userId,
                HttpMethod.DELETE,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response updateUser(UserDto userDto, HttpHeaders headers) {
        LOGGER.info("UPDATE USER: " + userDto.toString());
        HttpEntity requestEntity = new HttpEntity(userDto, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                adminUserProperties.getUrl(),
                HttpMethod.PUT,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response addUser(UserDto userDto, HttpHeaders headers) {
        LOGGER.info("ADD USER INFO : "+userDto.toString());
        HttpEntity requestEntity = new HttpEntity(userDto, headers);
        ResponseEntity<Response<User>> re = restTemplate.exchange(
                adminUserProperties.getUrl() + "/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Response<User>>() {
                });

        return re.getBody();
    }
}
