package www.OliveOnline.com.OliveShop.entity;

import java.util.ArrayList;
import java.util.List;

public class BasketData {
    public static List<Product> basket;
    static {
        basket=new ArrayList<>();
    }
}
