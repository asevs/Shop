package wsiz.groupproject.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wsiz.groupproject.demo.service.OrderRepository;
import wsiz.groupproject.demo.model.Order;
import wsiz.groupproject.demo.model.Product;
import wsiz.groupproject.demo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/allorders")
    List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    Optional<Order> getProduct(@RequestParam long id){

        Optional<Order> byId = orderRepository.findById(id);
        return byId;
    }


    @PostMapping("/createOrder")
    public Order addOrder(@RequestParam Product product, User user){
        Order _order = new Order(product, user);
        orderRepository.save(_order);
        return _order;
    }
    @DeleteMapping("/removeOrder/{id}")
    String deleteProduct(@PathVariable long id){
        try{
            orderRepository.deleteById(id);
        }
        catch (Exception e){
            return "Error " + e;
        }
        System.out.println("Orders after DELETE");
        System.out.println(orderRepository.findAll());
        return "Done";
    }


}
