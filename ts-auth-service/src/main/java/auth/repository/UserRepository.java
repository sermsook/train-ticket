package auth.repository;

import auth.entity.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author fdse
 */
public interface UserRepository extends MongoRepository<AuthUser, String> {

    /**
     * find by username
     *
     * @param username username
     * @return Optional<User>
     */
    Optional<AuthUser> findByUsername(String username);

    /**
     * delete by user id
     *
     * @param userId user id
     * @return null
     */
    void deleteByUserId(UUID userId);
}
