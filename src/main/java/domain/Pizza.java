package domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Integer pizzaId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public Pizza(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
