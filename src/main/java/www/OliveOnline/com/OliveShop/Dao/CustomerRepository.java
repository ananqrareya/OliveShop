package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Customer;
import www.OliveOnline.com.OliveShop.entity.Users;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUser(Users user);
}
