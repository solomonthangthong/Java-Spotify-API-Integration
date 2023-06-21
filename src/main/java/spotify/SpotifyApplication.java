package spotify;

import se.michaelthelin.spotify.SpotifyApi;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SpotifyApplication {
    public static void main(String[] args) {

        AppView appView = new AppView();

        String clientID = null;
        String clientSecret = null;
        String redirectUriString = null;
        URI redirectUri = null;

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
            clientID = properties.getProperty("client.id");
            clientSecret = properties.getProperty("client.secret");
            redirectUriString = properties.getProperty("redirect.uri");
            System.out.println(clientID + clientSecret + redirectUriString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            redirectUri = new URI(redirectUriString);
        }  catch (URISyntaxException e){
            e.printStackTrace();
        }

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();

        appView.setResizable(false);
        appView.setVisible(true);
    }
}
