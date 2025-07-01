package com.npci.web;

import com.npci.dto.TransferRequest;
import com.npci.dto.TransferResponse;
import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.service.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransferController {

    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/transfer-form"
    )
    public String showTransferForm() {
        // Logic to display the transfer form
        return "transfer-form"; // This should return the name of the view template
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/transfer"
    )
    public String handleTransfer(
            @ModelAttribute TransferRequest request, // dto for transfer request
            Model model
            ) {

        String fromAccountNumber = request.getFromAccountNumber();
        String toAccountNumber = request.getToAccountNumber();
        double amount = request.getAmount();

        try {
            transferService.initiateTransfer(fromAccountNumber, toAccountNumber, amount);
            TransferResponse transferResponse = new TransferResponse();
            transferResponse.setStatus("success");
            transferResponse.setMessage("Transfer successful");
            model.addAttribute("transferResponse", transferResponse);
            return "transfer-success"; // Redirect to success page
        } catch (AccountNotFoundException | AccountBalanceException e) {
            TransferResponse transferResponse = new TransferResponse();
            transferResponse.setStatus("failure");
            transferResponse.setMessage(e.getMessage());
            model.addAttribute("transferResponse", transferResponse);
            return "transfer-error"; // Redirect to failure page
        }

    }


}
