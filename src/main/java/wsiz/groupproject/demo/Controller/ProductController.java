package wsiz.groupproject.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wsiz.groupproject.demo.model.Category;
import wsiz.groupproject.demo.model.Product;
import wsiz.groupproject.demo.service.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/allProducts")
    List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/promoProducts")
    List<Product> getPromoProducts() {
        List<Product> promoProducts = (List<Product>) productRepository.findAll();
        return promoProducts
                .stream()
                .filter(product -> product.getPromoPrice() != null)
                .collect(Collectors.toList());
    }

    @GetMapping("/coffeeBeans")
    List<Product> getCoffeeBeans() {
        List<Product> coffeeBeans = (List<Product>) productRepository.findAll();
        return coffeeBeans
                .stream()
                .filter(coffee -> coffee.getCategory() == Category.COFFEE_BEANS)
                .collect(Collectors.toList());
    }

    @GetMapping("/groundCoffees")
    List<Product> getGroundCoffees() {
        List<Product> groundCoffees = (List<Product>) productRepository.findAll();
        return groundCoffees
                .stream()
                .filter(coffee -> coffee.getCategory() == Category.GROUND_COFFEE)
                .collect(Collectors.toList());
    }

    @GetMapping("/accessories")
    List<Product> getAccessories(List<Product> accessories) {
        return accessories
                .stream()
                .filter(accessory -> accessory.getCategory() == Category.ACCESSORY)
                .collect(Collectors.toList());
    }

    @PostMapping("/addProduct")
    Product addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }

    //    @GetMapping("/product/{id}")
//    Optional<Product> getProduct(@PathVariable long id){
//
//        Optional<Product> byId = productRepository.findById(id);
//        return byId;
//    }
//

//    @DeleteMapping("delete/{id}")
//    String deleteProduct(@PathVariable long id){
//        try{
//            productRepository.deleteById(id);
//        }
//        catch (Exception e){
//            return "Error";
//        }
//        System.out.println("Customer Stores after DELETE ");
//        System.out.println(productRepository.findAll());
//        return "Done";
//    }

//    @GetMapping("/getPromotions")
//    List<Product> getPromoProducts(){
//        return productRepository.findByIsPromoTrue();
//    }
}