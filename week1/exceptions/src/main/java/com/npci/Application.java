package com.npci;

class AccountNotFoundException extends Throwable {
    String accountId;

    public AccountNotFoundException(String accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return "Account not found with id: " + accountId;
    }
}

//----------------------------------------------------
// Service Layer -> business Logic...
//----------------------------------------------------

class TransferRequest {
    private double amount;
    private String from;
    private String to;

    public TransferRequest(double amount, String from, String to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

class TransferResponse {
    private long transactionId;
    private String message;

    public TransferResponse(long transactionId, String message) {
        this.transactionId = transactionId;
        this.message = message;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }
}

class TransferService {
    public TransferResponse transfer(TransferRequest transferRequest) throws AccountNotFoundException {
        // load 'from' & 'to' account details
        // check 'accounts' exist
        String fromAccountId = transferRequest.getFrom();
        if (fromAccountId.equals("999")) {
            throw new AccountNotFoundException(fromAccountId);
        }
        // check 'from' has enough balance
        // debit
        // credit
        // update 'from' and 'to' accounts
        TransferResponse transferResponse = new TransferResponse(123, "success");
        return transferResponse;
    }
}


//----------------------------------------------------
// Web/Api Layer -> request & response Logic...
//----------------------------------------------------

class TransferApiController {
    TransferService transferService = new TransferService();
    public void handleTransfer(double amount, String from, String to) {
        TransferRequest transferRequest = new TransferRequest(amount, from, to);
        try {
            TransferResponse transferResponse = transferService.transfer(transferRequest);
            System.out.println(transferResponse.getTransactionId() + "\t " + transferResponse.getMessage());
        } catch (AccountNotFoundException e) {
            // log exception in log file
            // report to user with friendly messages
            System.out.println("Exception: " + e.getMessage());
            // re-throw
            // execute plan-B action
            // release/clean any external resources ( e.g connection ) used..
        }
    }
}


public class Application {

    public static void main(String[] args) {

        TransferApiController transferApiController = new TransferApiController();
        transferApiController.handleTransfer(100.00, "999", "2");


    }

}
