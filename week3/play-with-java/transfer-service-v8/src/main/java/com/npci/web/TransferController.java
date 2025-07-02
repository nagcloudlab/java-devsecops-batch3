package com.npci.web;

import com.npci.dto.TransferRequest;
import com.npci.dto.TransferResponse;
import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/v1/transfer",
            consumes = "application/json",
            produces = "application/json"
    )
    public TransferResponse handleTransfer(
            @RequestBody TransferRequest transferRequest
    ) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated user: " + authentication.getName());

        TransferResponse transferResponse = new TransferResponse();
        try {
            transferService.initiateTransfer(
                    transferRequest.getFromAccount(),
                    transferRequest.getToAccount(),
                    transferRequest.getAmount());
            transferResponse.setStatus("success");
            transferResponse.setMessage("transfer successful");
            transferResponse.setTransactionId("12121212");
            return transferResponse;
        } catch (AccountNotFoundException | AccountBalanceException ex) {
            // Handle exceptions and return an error view
            // You can log the exception or show an error message to the user
            //ex.printStackTrace();
            transferResponse.setStatus("error");
            transferResponse.setMessage(ex.getMessage());
            return transferResponse;
        }
    }

}
