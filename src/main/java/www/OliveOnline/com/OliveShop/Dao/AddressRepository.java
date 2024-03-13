package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Address;

public interface AddressRepository  extends JpaRepository<Address,Integer> {
}
