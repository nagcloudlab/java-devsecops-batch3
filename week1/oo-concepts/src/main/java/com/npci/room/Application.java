package com.npci.room;

import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {

        Door door = new Door();

        Light light = new Light();
        AC ac = new AC();

        door.addListener(light);
        door.addListener(ac);

        Thread.sleep(2000);
        door.open();
        Thread.sleep(2000);
        door.close();

        door.removeListener(ac);

        Thread.sleep(2000);
        door.open();
        Thread.sleep(2000);
        door.close();

    }
}
