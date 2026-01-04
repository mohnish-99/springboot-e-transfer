package springlearning.io.e_transfer_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Transfer {

    @Id
    private String transactionId;

    private Long senderId;
    private Long receiverId;

    private BigDecimal amount;

    @Enumerated
    private TransferStatus status;

    private LocalDateTime createdAt;
}
