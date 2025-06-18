package com.npci.room;

public class Light implements DoorListener {

    public  void on(){
        System.out.println("Light is ON");
    }

    public  void off(){
        System.out.println("Light is OFF");
    }

}
