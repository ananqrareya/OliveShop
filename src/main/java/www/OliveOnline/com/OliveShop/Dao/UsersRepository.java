package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Roles;
import www.OliveOnline.com.OliveShop.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    List<Users> findByRole(Roles roles);
    Optional<Users>findUsersByEmail(String email);
}
