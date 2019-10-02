package wsiz.groupproject.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import wsiz.groupproject.demo.DemoApplication;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate date;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(Product product, User user) {
        date = LocalDate.now();
        status = Status.ORDERED;
    }

}
