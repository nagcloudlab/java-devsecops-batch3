package com.npci;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloServer {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(7070), 0);
        server.createContext("/hello", exchange -> {
            System.out.println("Received request from: " + exchange.getRemoteAddress());
            Runnable task = () -> handleHello(exchange);
            //new Thread(task).start();
            // Using ExecutorService to handle requests
            executorService.submit(task);
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Hello server started on http://localhost:7070");

    }

    private static void handleHello(HttpExchange exchange) {
        try (exchange) {
            Thread.sleep(1000); // Simulate I/O delay
            String response = "Hello from thread: " + Thread.currentThread();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
