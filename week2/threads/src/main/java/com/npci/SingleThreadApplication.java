package com.npci;



/*

    Problems with single-threaded approach:
    -> If the transfer takes time, the next transfer request will have to wait.
    -> not utilizing the full potential of the system.
    -> application not responsive, end user experience is poor.

 */

public class SingleThreadApplication {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        TransferService transferService = new TransferService();

        // step-1 : task1 : handle transfer request
        transferService.transfer("A", "B", 1000);

        // step-2: task2: handle transfer request
        transferService.transfer("C", "D", 1000);

    }
}
