package com.npci.room;

import java.util.ArrayList;
import java.util.List;

public class Door {

    private List<DoorListener> listeners = new ArrayList<>(); // data-structure to hold listeners

    public void addListener(DoorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(DoorListener listener) {
        listeners.remove(listener);
    }

    public void open() {
        System.out.println("Door is opened");
        for (DoorListener listener : listeners) {
            listener.on();
        }
    }

    public void close() {
        System.out.println("Door is closed");
        for (DoorListener listener : listeners) {
            listener.off();
        }
    }

}
