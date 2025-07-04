package org.npci.api;

import jakarta.validation.Valid;
import org.npci.dto.TransferRequest;
import org.npci.dto.TransferResponse;
import org.npci.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = "/transfer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TransferResponse> initiateTransfer(@Valid @RequestBody TransferRequest transferRequest) {

        transferService.initiateTransfer(
                transferRequest.getFromAccountNumber(),
                transferRequest.getToAccountNumber(),
                transferRequest.getAmount()
        );

        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setTransactionId("TXN123456789");
        transferResponse.setStatus("SUCCESS");
        transferResponse.setFromAccountNumber(transferRequest.getFromAccountNumber());
        transferResponse.setToAccountNumber(transferRequest.getToAccountNumber());
        transferResponse.setAmount(transferRequest.getAmount());

        return ResponseEntity.ok(transferResponse);
    }
}
