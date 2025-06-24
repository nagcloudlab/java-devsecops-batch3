package com.npci;

import java.sql.Connection;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Eager_vs_Lazy_Computation {


    private static String getSystemStatus() {
        System.out.println("getting system status");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "System Status " + new Date().getTime();
    }

    public static void main(String[] args) {


        String conn = null;
        // validating is conn null, if log message with system-status
        Objects.requireNonNull(conn, ()->"Connection is null  - " + getSystemStatus());

    }
}
