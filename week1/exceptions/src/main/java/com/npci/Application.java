package com.npci;


/*

     2 types of Throwable

        - Error  => something serious  e.g oom, stack-overflow
        - Exception => unexpected events  ( checked )
            - RuntimeException ( unchecked ) - for debugging..


 */

//--------------------------------------------------
// Exception classes
//--------------------------------------------------

class AccountNotFoundException extends Exception {
    private String account;

    public AccountNotFoundException(String account) {
        this.account = account;
    }

    public String getMessage() {
        return "Account not found - " + account;
    }
}

class AccountBalanceException extends Exception {
    private String account;
    private double balance;

    public AccountBalanceException(String account, double balance) {
        this.account = account;
        this.balance = balance;
    }

    public String getMessage() {
        return "Account no enough balance - " + account + "," + balance;
    }
}

//--------------------------------------------------
// service / business layer
//--------------------------------------------------
class TransferService {
    public void transfer(String from, String to, double amount) throws AccountNotFoundException, AccountBalanceException {
        // load 'from' and 'to' account details
        boolean isFromAccountExist = true;
        if (!isFromAccountExist) {
            throw new AccountNotFoundException(from);
        }
        double currentBalance = 1000.00;
        if (currentBalance < amount) {
            throw new AccountBalanceException(from, currentBalance);
        }
        //..
        // debit
        // credit
        //...
    }
}


//--------------------------------------------------
// api / web layer -> request & response logic
//--------------------------------------------------
class TransferController {
    TransferService transferService = new TransferService();

    public void handleTransferRequest(String from, String to, double amount) {
        //..
        try {
            transferService.transfer(from, to, amount);
            System.out.println("Transfer successful");
        } catch (AccountNotFoundException e) {
            // // friendly messages
            System.out.println("Ex - " + e.getMessage());
        } catch (AccountBalanceException e) {
            System.out.println("Ex - " + e.getMessage());
        }
    }
}


class Employee {
    void doWork() {
        System.out.println("Employee works");
    }
}

public class Application {
    public static void main(String[] args) {

        TransferController transferController = new TransferController();
        transferController.handleTransferRequest("1", "2", 1000.00);


        try {
            Employee employee = new Employee();
            employee.doWork();

            int[] numbers = {1, 2, 3, 4, 5};
            System.out.println(numbers[10]);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - " + e.getMessage());
        }


    }
}
