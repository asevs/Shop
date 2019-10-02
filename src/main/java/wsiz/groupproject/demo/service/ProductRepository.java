package wsiz.groupproject.demo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wsiz.groupproject.demo.model.Product;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByIsPromoTrue();
}
