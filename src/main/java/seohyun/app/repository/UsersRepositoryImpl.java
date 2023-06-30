package seohyun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seohyun.app.models.Users;

import java.util.List;

public interface UsersRepositoryImpl extends JpaRepository<Users, String> {
 }
