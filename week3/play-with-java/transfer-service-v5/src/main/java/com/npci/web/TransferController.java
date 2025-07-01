package com.npci.web;

import com.npci.dto.TransferRequest;
import com.npci.dto.TransferResponse;
import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        // This method will return the view name for the transfer form
        System.out.println("-".repeat(50));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current User: " + authentication.getName());
        authentication.getAuthorities().forEach(authority -> {
            System.out.println("Authority: " + authority.getAuthority());
        });
        System.out.println("-".repeat(50));
        return "transfer-form"; // Assuming you have a view named 'transfer-form.html'
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/transfer"
    )
    public String handleTransfer(
            @ModelAttribute TransferRequest transferRequest,
            Model model
    ) {
        // This method will handle the transfer logic
        // You can add your business logic here
        TransferResponse transferResponse = new TransferResponse();
        try {
            transferService.initiateTransfer(
                    transferRequest.getFromAccount(),
                    transferRequest.getToAccount(),
                    transferRequest.getAmount());
            transferResponse.setStatus("success");
            transferResponse.setMessage("transfer successful");
            transferResponse.setTransactionId("12121212");
            model.addAttribute("transferResponse", transferResponse);
            return "transfer-success"; // Assuming you have a view named 'transfer-success.html'
        } catch (AccountNotFoundException | AccountBalanceException ex) {
            // Handle exceptions and return an error view
            // You can log the exception or show an error message to the user
            //ex.printStackTrace();
            transferResponse.setStatus("error");
            transferResponse.setMessage(ex.getMessage());
            model.addAttribute("transferResponse", transferResponse);
            return "transfer-error"; // Assuming you have a view named 'transfer-error.html'
        }
    }

}
