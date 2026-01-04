package springlearning.io.e_transfer_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  Long userId;

    private BigDecimal balance;


    @Version          // Optimistic locking
    private Integer version;


    public void debit(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }

}
