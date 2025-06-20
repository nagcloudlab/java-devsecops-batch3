package com.npci.behavior.observer;

import java.util.ArrayList;
import java.util.List;

interface Notifier {
    void notify(String message);
}

class EmailNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("Email notification: " + message);
    }
}

class SMSNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("SMS notification: " + message);
    }
}


class TransferService {

//    private Notifier notifier;  // One Notifier
//
//    public TransferService(Notifier notifier) {
//        this.notifier = notifier;
//    }

    List<Notifier> notifiers = new ArrayList<>();  // Zero or Many

    public void addNotifier(Notifier notifier) {
        notifiers.add(notifier);
    }

    public void removeNotifier(Notifier notifier) {
        notifiers.remove(notifier);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        // Logic for transferring money
        System.out.println("Transferring " + amount + " from " + fromAccount + " to " + toAccount);
        // Notify after transfer
        //notifier.notify("Transfer of " + amount + " from " + fromAccount + " to " + toAccount + " completed.");

//        for (Notifier notifier : notifiers) {
//            notifier.notify("Transfer of " + amount + " from " + fromAccount + " to " + toAccount + " completed.");
//        }

        // send this message to 'kafka'

    }
}


public class Application {
    public static void main(String[] args) {

        EmailNotifier emailNotifier = new EmailNotifier();
        SMSNotifier smsNotifier = new SMSNotifier();

        TransferService transferService = new TransferService();
        transferService.addNotifier(emailNotifier);
        transferService.addNotifier(smsNotifier);


        transferService.transfer("Account1", "Account2", 100.0);

    }
}
