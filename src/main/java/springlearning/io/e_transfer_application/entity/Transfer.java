package springlearning.io.e_transfer_application.entity;

import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    @Enumerated
    private TransferStatus status;

    private LocalDateTime createdAt;
}
