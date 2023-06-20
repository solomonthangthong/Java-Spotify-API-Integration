package spotify;

import se.michaelthelin.spotify.*;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class SpotifyApi {
    public static void main(String[] args) {
        int port = 8080;
        LocalRedirectURI server = new LocalRedirectURI(port);
        /* Instance of config file that containers environment variables for client ID, secret, etc */
        Properties properties = new Properties();

        try (InputStream input = SpotifyApi.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            server.startServer();
        } catch (IOException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
            e.printStackTrace();
        }

        String clientID = properties.getProperty("client.id");
        String clientSecret = properties.getProperty("client.secret");
        String redirectUri = properties.getProperty("redirect.uri");
    }
}
