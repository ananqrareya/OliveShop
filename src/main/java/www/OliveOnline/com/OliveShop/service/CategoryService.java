package www.OliveOnline.com.OliveShop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.OliveOnline.com.OliveShop.Dao.CategoryRepository;
import www.OliveOnline.com.OliveShop.entity.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
@Autowired
    private CategoryRepository categoryRepository;
public List<Category> getAllCategory(){
    return categoryRepository.findAll();
}
public void AddCategory(Category category){
    categoryRepository.save(category);
}
 public Category findById(int id){
    Optional<Category>category=categoryRepository.findById(id);
    Category category1=null ;
    if (category.isPresent())
        category1=category.get();
    else
        throw  new RuntimeException("Not Found Category id -"+id);
    return category1;
 }
 public void deleteById(int id){
    categoryRepository.deleteById(id);
 }
}
