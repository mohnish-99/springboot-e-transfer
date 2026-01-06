package springlearning.io.e_transfer_application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.math.BigDecimal;

public record TransferRequest(
                              @NotBlank String idempotencyKey,
                              @NonNull Long fromUserId,
                              @NonNull String toIdentifier,
                              @NonNull @DecimalMax("3000.00") BigDecimal amount
                              ) {
}
