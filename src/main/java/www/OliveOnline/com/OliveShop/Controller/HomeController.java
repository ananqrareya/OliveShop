package www.OliveOnline.com.OliveShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import www.OliveOnline.com.OliveShop.entity.BasketData;
import www.OliveOnline.com.OliveShop.service.CategoryService;
import www.OliveOnline.com.OliveShop.service.ProductsService;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductsService productsService;

    @GetMapping({"/","/home"})
    public String home(Model model){

        model.addAttribute("basketCount", BasketData.basket.size());
        return "index";
    }
@GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productsService.getAllProduct());
    model.addAttribute("basketCount", BasketData.basket.size());
        return "shop";
}
@GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
    model.addAttribute("basketCount", BasketData.basket.size());
        model.addAttribute("products",productsService.findByCategory(id));
        return "shop";
}
@GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable  int id){
        model.addAttribute("product",productsService.findById(id));
    model.addAttribute("basketCount", BasketData.basket.size());
        return "viewProduct";
}
}
