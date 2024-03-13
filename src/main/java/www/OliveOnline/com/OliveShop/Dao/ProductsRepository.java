package www.OliveOnline.com.OliveShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import www.OliveOnline.com.OliveShop.entity.Product;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByCategory_Id(int id);
}
