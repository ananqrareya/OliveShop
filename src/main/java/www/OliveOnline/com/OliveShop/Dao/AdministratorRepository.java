package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Administrator;
import www.OliveOnline.com.OliveShop.entity.Users;

public interface AdministratorRepository extends JpaRepository<Administrator,Integer> {
    Administrator findByUser(Users user);

}
