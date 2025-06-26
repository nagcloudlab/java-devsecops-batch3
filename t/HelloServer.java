import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HelloServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(7070), 0);
        server.createContext("/hello", exchange -> {
            Thread.startVirtualThread(() -> handleHello(exchange));
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Hello server started on http://localhost:7070");
    }

    private static void handleHello(HttpExchange exchange) {
        try (exchange) {
            Thread.sleep(100); // Simulate I/O delay
            String response = "Hello from virtual thread: " + Thread.currentThread();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
