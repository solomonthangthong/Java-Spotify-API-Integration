package spotify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class AppView extends JFrame implements ActionListener, Serializable {
    public AppView() {
        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("Spotify Statistics - by Solomon Thangthong");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
