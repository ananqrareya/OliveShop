package www.OliveOnline.com.OliveShop.Dto;

import lombok.Data;

@Data
public class ProductDto {
    int id;
    String name;
    double price;
    double weight ;
    String description ;
    String imageName;
    int categoryId;

}
