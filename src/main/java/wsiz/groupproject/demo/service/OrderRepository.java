package wsiz.groupproject.demo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wsiz.groupproject.demo.model.Order;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
}
