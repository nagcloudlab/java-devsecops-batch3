package org.npci.api;

import org.npci.dto.TransferRequest;
import org.npci.dto.TransferResponse;
import org.npci.exception.AccountBalanceException;
import org.npci.exception.AccountNotFoundException;
import org.npci.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransferController {

    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    // Define endpoints for transfer operations here
    // For example:
    @PostMapping(
            value = "/api/v1/transfer",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> initiateTransfer(@RequestBody TransferRequest transferRequest) {

        try {
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
        }catch (AccountNotFoundException e){
            return ResponseEntity.status(404).body("Account not found: " + e.getMessage());
        } catch (AccountBalanceException e) {
            return ResponseEntity.status(400).body("Insufficient balance: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing the transfer: " + e.getMessage());
        }

    }


}
