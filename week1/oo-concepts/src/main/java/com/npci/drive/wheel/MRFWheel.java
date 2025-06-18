package com.npci.drive.wheel;

public class MRFWheel implements Wheel {

    public MRFWheel() {
        System.out.println("MRF-Wheel is created");
    }

    public int rotate(int speed) {
        System.out.println("MRF-Wheel is rotating");
        return speed * 2; // Example logic for rotation
    }
}
