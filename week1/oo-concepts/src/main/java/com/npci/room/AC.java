package com.npci.room;

public class AC implements DoorListener {
    public  void on(){
        System.out.println("AC is ON");
    }
    public  void off(){
        System.out.println("AC is OFF");
    }
}
