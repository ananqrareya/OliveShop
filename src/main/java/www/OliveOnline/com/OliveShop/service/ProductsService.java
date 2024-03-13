package www.OliveOnline.com.OliveShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.OliveOnline.com.OliveShop.Dao.ProductsRepository;
import www.OliveOnline.com.OliveShop.Dto.ProductDto;
import www.OliveOnline.com.OliveShop.entity.Category;
import www.OliveOnline.com.OliveShop.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    public List<Product> getAllProduct(){
        return productsRepository.findAll();
    }
    public void saveProducts(Product product){
        productsRepository.save(product);
    }
    public void deleteProductById(int id){
        productsRepository.deleteById(id);
    }
    public Product findById(int id){
        Optional<Product> product=productsRepository.findById(id);
       Product product1=null;
        if (product.isPresent())
            product1=product.get();
        else
            throw  new RuntimeException("Not Found Product id -"+id);
        return product1;
    }
    public List<Product> findByCategory(int id){
        return productsRepository.findAllByCategory_Id(id);
    }
}
