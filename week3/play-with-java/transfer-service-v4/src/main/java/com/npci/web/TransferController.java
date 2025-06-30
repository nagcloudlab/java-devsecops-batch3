package com.npci.web;

import com.npci.service.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            HttpServletRequest request
    ) {
        // Logic to process the transfer

        String fromAccountNumber = request.getParameter("fromAccountNumber");
        String toAccountNumber = request.getParameter("toAccountNumber");
        double amount = Double.parseDouble(request.getParameter("amount"));

        // validation...

        // initiate transfer using the service
        transferService.initiateTransfer(fromAccountNumber, toAccountNumber, amount);

        // This could include validation, calling a service, etc.
        return "transfer-success"; // This should return the name of the success view template
    }


}
