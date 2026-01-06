package springlearning.io.e_transfer_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springlearning.io.e_transfer_application.dto.TransferRequest;
import springlearning.io.e_transfer_application.dto.TransferResponse;
import springlearning.io.e_transfer_application.service.TransferService;

@RestController
@RequestMapping("api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest transferRequest)
    {
        return ResponseEntity.ok(transferService.transfer(transferRequest));
    }
}
