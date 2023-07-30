package seohyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seohyun.app.models.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {

    List<Users> findAll();
    Users findOneById(String id);

    Users findOneByUserId(String userId);

    Users findByUserIdAndPassword(String userId, String password);

    Boolean existsByUserId(String userId);

    Boolean existsByUsername(String username);
    }
