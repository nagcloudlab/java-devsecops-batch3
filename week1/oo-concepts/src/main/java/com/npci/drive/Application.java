package com.npci.drive;

import com.npci.drive.car.Car;
import com.npci.drive.wheel.CEATWheel;
import com.npci.drive.wheel.MRFWheel;

public class Application {
    public static void main(String[] args) {

        MRFWheel mrfWheel = new MRFWheel();
        Car car = new Car(mrfWheel);
        System.out.println();

        car.move();

        System.out.println();

        car.move();

        CEATWheel ceatWheel = new CEATWheel();
        car.setWheel(ceatWheel);

        System.out.println();
        car.move();

    }
}
