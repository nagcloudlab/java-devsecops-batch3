package com.npci;

import java.io.Closeable;

// file , database , n/w sockets, device...
class ExternalResource implements Closeable {
    public void init() {
        System.out.println("Init()");
    }

    public void use() throws Exception {
        System.out.println("Use()");
        boolean isFailure = false;
        if (isFailure) {
            throw new Exception();
        }
    }

    public void close() {
        System.out.println("Close()");
    }
}

public class Application2 {
    public static void main(String[] args) {

        ExternalResource resource = new ExternalResource();
        resource.init();

        // try with closable resource
        try (resource) {
            resource.use();
            System.out.println("success");
            return;
            //resource.close();
        } catch (Exception e) {
            System.out.println("failure");
            //resource.close();
        }
//        finally {
//            resource.close();
//        }

    }
}
