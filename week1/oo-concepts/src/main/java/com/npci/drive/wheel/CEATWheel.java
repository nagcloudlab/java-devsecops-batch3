package com.npci.drive.wheel;

public class CEATWheel implements Wheel {

    public CEATWheel() {
        System.out.println("CEAT-Wheel is created");
    }

    public int rotate(int speed) {
        System.out.println("CEAT-Wheel is rotating");
        // Example logic for rotation
        int rpm = speed * 3; // Assuming CEAT wheel has a different rotation logic
        return rpm;
    }
}
