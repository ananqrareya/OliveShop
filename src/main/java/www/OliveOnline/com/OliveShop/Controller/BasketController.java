package www.OliveOnline.com.OliveShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import www.OliveOnline.com.OliveShop.entity.BasketData;
import www.OliveOnline.com.OliveShop.entity.Product;
import www.OliveOnline.com.OliveShop.service.ProductsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {
    @Autowired
    private ProductsService productsService;
    @GetMapping("/addToBasket/{id}")
    public String addToProduct(@PathVariable int id){
        BasketData.basket.add(productsService.findById(id));
        return "redirect:/shop";
    }
    @GetMapping("basket")
    public String getBasket(Model model) {
        model.addAttribute("basketCount", BasketData.basket.size());
        model.addAttribute("total", BasketData.basket.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("basket",BasketData.basket);
        return "basket";
    }
    @GetMapping("/basket/removeItem/{index}")
    public String removeItems(@PathVariable int index){
        BasketData.basket.remove(index);
        return "redirect:/basket";
    }
    @GetMapping("/checkout")
    public String getCheckout(Model model){
        model.addAttribute("basketCount", BasketData.basket.size());
        model.addAttribute("total", BasketData.basket.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
    @PostMapping("/payNow")
    public String PayNow(Model model){
        List<Product> products = BasketData.basket;
        List<Map<String, String>> productDetails = new ArrayList<>();
        for (Product product : products) {
            Map<String, String> details = new HashMap<>();
            details.put("Product Name", product.getName());
            details.put("Price", String.format("₪%.2f", product.getPrice()));
            productDetails.add(details);
        }
        model.addAttribute("productDetails", productDetails);
        model.addAttribute("result", "Your receipt details:");
        return "orderPlaced";
    }
}