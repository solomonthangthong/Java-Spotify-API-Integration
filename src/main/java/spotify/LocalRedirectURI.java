package spotify;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class LocalRedirectURI {
    private final int port;

    public LocalRedirectURI(int port) {
        this.port = port;
    }

    /**
     * Start a local server for the Redirect URI
     * @throws IOException
     */
    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/callback", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port " + port);
    }

    /**
     * Represents a handler for processing HTTP requests in the server.
     */
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Authorization successful!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
