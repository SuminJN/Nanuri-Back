package walab.nanuri.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import walab.nanuri.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}