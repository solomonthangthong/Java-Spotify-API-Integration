package spotify;

import se.michaelthelin.spotify.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class SpotifyApi {
    public static void main(String[] args) {
        int port = 8080;
        LocalRedirectURI server = new LocalRedirectURI(port);
        /* Instance of config file that containers environment variables for client ID, secret, etc */
        Properties properties = new Properties();

        try {
            server.startServer();
        } catch (IOException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
            String clientID = properties.getProperty("client.id");
            String clientSecret = properties.getProperty("client.secret");
            String redirectUri = properties.getProperty("redirect.uri");

            System.out.println(clientID + clientSecret + redirectUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpotifyApi spotifyApi = new SpotifyApi();

    }
}
