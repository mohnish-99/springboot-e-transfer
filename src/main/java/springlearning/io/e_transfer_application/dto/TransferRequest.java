package springlearning.io.e_transfer_application.dto;

import jakarta.validation.constraints.DecimalMax;
import lombok.NonNull;

import java.math.BigDecimal;

public record TransferRequest(@NonNull Long fromUserId,
                              @NonNull Long toIdentifier,
                              @NonNull @DecimalMax("3000.00") BigDecimal amount
                              ) {
}
