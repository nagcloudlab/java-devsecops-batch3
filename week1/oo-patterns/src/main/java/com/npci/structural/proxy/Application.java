package com.npci.structural.proxy;


/*

    design issues
    ----------------
    -> code tangling / coupling
    -> code scattering / duplication

    Design Solution : Proxy Pattern

 */

class Greeting {
    public void hello() {
        System.out.println("Hello");
    }

    public void hi() {
        System.out.println("Hi");
    }
}

class Emoji {
    public void printSmile() {
        System.out.println("😀");
    }
}
class Auth {
    public void auth() {
        System.out.println("Auth Check");
    }
}
class GreetingProxy {
    Greeting greeting = new Greeting();
    Emoji emoji = new Emoji();
    Auth auth = new Auth();

    public void hello() {
        auth.auth();
        greeting.hello();
        emoji.printSmile();
    }

    public void hi() {
        auth.auth();
        greeting.hi();
        emoji.printSmile();
    }
}

public class Application {
    public static void main(String[] args) {
        GreetingProxy proxy = new GreetingProxy();
        proxy.hello();

    }
}
