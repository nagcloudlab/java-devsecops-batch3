package com.npci.drive.car;


import com.npci.drive.wheel.Wheel;

// principle : Open for extension, closed for modification
// how to implement above principle?
// dependent & dependencies must separated by interfaces
public class Car {
    private Wheel wheel;

    public Car(Wheel wheel) {
        this.wheel = wheel;
        System.out.println("Car is created");
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public void move() {
        int rpm = wheel.rotate(100);
        System.out.println("Car is moving with rpm: " + rpm);
    }
}
