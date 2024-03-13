package www.OliveOnline.com.OliveShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.OliveOnline.com.OliveShop.Dao.CategoryRepository;
import www.OliveOnline.com.OliveShop.Dto.ProductDto;
import www.OliveOnline.com.OliveShop.entity.Category;
import www.OliveOnline.com.OliveShop.entity.Product;
import www.OliveOnline.com.OliveShop.service.CategoryService;
import www.OliveOnline.com.OliveShop.service.ProductsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminController {
    public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductsService productsService;
@GetMapping("/admin")
    public String adminHome(){
    return "adminHome";
}
@GetMapping("/admin/categories")
    public String getCategories(Model model){
    model.addAttribute("categories",categoryService.getAllCategory());
    return "categories";
}

@GetMapping("/admin/categories/add")
public String getAddCategories(Model model){
    model.addAttribute("categories",new Category());
    return "categoriesAdd";
}
@PostMapping("/admin/categories/add")
public String postAddCategories(@ModelAttribute("categories")Category category){
categoryService.AddCategory(category);
return "redirect:/admin/categories";
}
@GetMapping("/admin/categories/delete/{id}")
public String getDeleteCategories(@PathVariable("id")int id){
    categoryService.deleteById(id);
    return "redirect:/admin/categories";
}
@GetMapping("/admin/categories/update/{id}")
public String getUpdateCategories(@PathVariable("id")int id,Model model){
    Category category=categoryService.findById(id);
    model.addAttribute("categories",category);
    return "categoriesAdd";
}

@GetMapping("/admin/products")
    public String getProduct(Model model){
    model.addAttribute("products",productsService.getAllProduct());
    return "products";
}
@GetMapping("/admin/products/add")
    public String getAddProducts(Model model){
    model.addAttribute("productDTO",new ProductDto());
    model.addAttribute("categories",categoryService.getAllCategory());
    return "productsAdd";
}
@PostMapping("/admin/products/add")
    public String addProducts(@ModelAttribute("productDTO")ProductDto productDto,
                                @RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
    Product product = new Product();
    product.setId(productDto.getId());
    Category category = categoryService.findById(productDto.getCategoryId());
    product.setCategory(category);
    product.setDescription(productDto.getDescription());
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    product.setWeight(productDto.getWeight());
    String imageUUID;
    if (!file.isEmpty()) {
        imageUUID=file.getOriginalFilename();
        Path  fileNameAndPath= Paths.get(uploadDir,imageUUID);
        Files.write(fileNameAndPath,file.getBytes());

    }else imageUUID=imgName;
    product.setImageName(imageUUID);
    productsService.saveProducts(product);
    return "redirect:/admin/products";
}
@GetMapping("/admin/product/delete/{id}")
    public String deleteProducts(@PathVariable("id")int id){
    productsService.deleteProductById(id);
    return "redirect:/admin/products";
}
@GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id,Model model){
    Product product=productsService.findById(id);
    ProductDto productDto=new ProductDto();
    productDto.setId(product.getId());
    productDto.setWeight(product.getWeight());
    productDto.setName(product.getName());
    productDto.setPrice(product.getPrice());
    productDto.setImageName(product.getImageName());
    productDto.setCategoryId(product.getCategory().getId());
    productDto.setDescription(product.getDescription());
    model.addAttribute("categories",categoryService.getAllCategory());
    model.addAttribute("productDTO",productDto);
    return "productsAdd";
}
}
