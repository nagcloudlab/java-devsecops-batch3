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
        // e,g amount is 1000, then this handler will handle 500
        // e.g if amount is 1200 , then next handler will be called
    }
}

public class Application {

    public static void main(String[] args) {

    }

}
