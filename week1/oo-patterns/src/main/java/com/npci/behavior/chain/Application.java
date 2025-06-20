package com.npci.behavior.chain;


interface Handler {
    void setNext(Handler next);

    void handleRequest(double amount);
}

class FiveHundredHandler implements Handler {
    private Handler nextHandler;

    public void setNext(Handler next) {
        this.nextHandler = next;
    }

    public void handleRequest(double amount) {
        if (amount >= 500) {
            int count = (int) (amount / 500);
            System.out.println("500 -> " + count);
            amount = amount - (count * 500);
        }
        if (amount > 0) {
            if (nextHandler != null) {
                nextHandler.handleRequest(amount);
            }
        }
    }
}

class TwoHundredHandler implements Handler {
    private Handler nextHandler;

    public void setNext(Handler next) {
        this.nextHandler = next;
    }

    public void handleRequest(double amount) {
        if (amount >= 200) {
            int count = (int) (amount / 200);
            System.out.println("200 -> " + count);
            amount = amount - (count * 200);
        }
        if (amount > 0) {
            if (nextHandler != null) {
                nextHandler.handleRequest(amount);
            }
        }
    }
}

class HundredHandler implements Handler {
    private Handler nextHandler;

    public void setNext(Handler next) {
        this.nextHandler = next;
    }

    public void handleRequest(double amount) {
        if (amount >= 100) {
            int count = (int) (amount / 100);
            System.out.println("100 -> " + count);
            amount = amount - (count * 100);
        }
        if (amount > 0) {
            if (nextHandler != null) {
                nextHandler.handleRequest(amount);
            }
        }
    }
}

public class Application {

    public static void main(String[] args) {

        Handler fiveHundredHandler = new FiveHundredHandler();
        Handler twoHundredHandler = new TwoHundredHandler();
        Handler hundredHandler = new HundredHandler();

        // chain-1
        twoHundredHandler.setNext(hundredHandler);
        fiveHundredHandler.setNext(twoHundredHandler);

        fiveHundredHandler.handleRequest(1300);


    }

}
