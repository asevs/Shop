package wsiz.groupproject.demo.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float rating;
    private String photo;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    private boolean isPromo;
    private BigDecimal promoPrice;
    private static Long numberOfSoldProducts = 0L;
    private static Long numberOfProductsInStock;


    public Product(BigDecimal price, Category category, BigDecimal promoPrice, Long numberOfProductsInStock, String name, String photo, float rating) {
        this.rating = rating;
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.category = category;
        this.promoPrice = promoPrice;
        isPromo = true;
        this.numberOfProductsInStock = numberOfProductsInStock;
    }

    public Product(BigDecimal price, Category category, Long numberOfProductsInStock, String name) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.numberOfProductsInStock = numberOfProductsInStock;
        isPromo = false;
    }

    public static void addProductsToStock(Long numberOfProducts){
        numberOfProductsInStock += numberOfProducts;
    }

    public static void soldProducts(Long id, Long numberOfProducts){
        numberOfSoldProducts += numberOfProducts;
        numberOfProductsInStock -= numberOfProducts;
    }
}
