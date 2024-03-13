package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Roles;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
}
