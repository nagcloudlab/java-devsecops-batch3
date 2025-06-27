package com.npci;

/*

    Limitations of Single Thread Application:

    -> It can only handle one transfer at a time.
    -> not utilizing the full potential of multi-core processors.
    -> not responsive to user interactions.

 */

public class SingleThreadApplication {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        // thread = step-by-step
        TransferService transferService = new TransferService();
        // step-1: handle transfer from A to B
        transferService.transfer("A", "B", 1000);
        // step-2: handle transfer from C to D
        transferService.transfer("C", "D", 2000);
    }
}