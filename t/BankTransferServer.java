import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class BankTransferServer {
    private static final ConcurrentHashMap<String, Double> accounts = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        accounts.put("alice", 1000.0);
        accounts.put("bob", 1000.0);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/transfer", exchange -> {
            Thread.startVirtualThread(() -> handleTransfer(exchange));
        });
        server.setExecutor(null); // Let virtual threads handle everything
        server.start();
        System.out.println("Server running on http://localhost:8080");
    }

    private static void handleTransfer(HttpExchange exchange) {
        try (exchange) {
            if (!"POST".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            String body = new String(exchange.getRequestBody().readAllBytes());
            // Very simple parser: from=alice&to=bob&amount=100
            String[] parts = body.split("&");
            String from = parts[0].split("=")[1];
            String to = parts[1].split("=")[1];
            double amount = Double.parseDouble(parts[2].split("=")[1]);

            synchronized (accounts) {
                if (!accounts.containsKey(from) || !accounts.containsKey(to)) {
                    respond(exchange, 400, "Invalid accounts");
                    return;
                }
                if (accounts.get(from) < amount) {
                    respond(exchange, 400, "Insufficient funds");
                    return;
                }
                accounts.put(from, accounts.get(from) - amount);
                accounts.put(to, accounts.get(to) + amount);
            }

            respond(exchange, 200, "Transfer successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void respond(HttpExchange exchange, int status, String msg) throws IOException {
        byte[] bytes = msg.getBytes();
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
    }
}

/*
 * 
 * 
 * curl -X POST http://localhost:8080/transfer \
 * -d "from=alice&to=bob&amount=100"
 * 
 */