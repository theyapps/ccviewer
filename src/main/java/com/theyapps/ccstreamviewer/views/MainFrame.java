package com.theyapps.ccstreamviewer.views;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Establishes the main frame for the application.
 */
public class MainFrame extends JFrame implements Observer {
    private static Logger log = Logger.getLogger(MainFrame.class);
    private VideoContentPanel contentPanel;
    private InfoPanel infoPanel;
    private JPanel mainPanel;

    private JDialog connectErrDialog;

    public MainFrame(){
        setTitle("CCStreamViewer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setFullscreen();
        initKeyListeners();

        // Set up placement of items on screen
        setupContentPanels();

        Zoneminder zm = Zoneminder.getInstance();
        zm.addObserver(this);
        zm.connect();

        setVisible(true);
        contentPanel.play();
    }

    /**
     * Sets up the panels for this frame
     */
    private void setupContentPanels() {
        mainPanel = new JPanel(new BorderLayout());

        contentPanel = new VideoContentPanel(this);
        infoPanel = new InfoPanel(this);

        mainPanel.add(infoPanel, BorderLayout.PAGE_START);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    /**
     * Sets the GUI frame to fullscreen mode
     */
    public void setFullscreen()
    {
        GraphicsDevice device = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        //setAlwaysOnTop(true);
        device.setFullScreenWindow(this); // Let the device context know that we are full screen
    }

    /**
     * Sets up and registers key listener for events that apply to the whole frame.
     */
    public void initKeyListeners(){
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}
        });
    }

    public boolean isFullscreenVideo(){
        return getContentPane() instanceof VideoPanel;
    }

    public void restoreContentPane(){
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    @Override
    public void update(Observable o, Object arg) {
        log.debug("Received observable response: " + arg);
        if(o instanceof Zoneminder){
            if(((Zoneminder) o).isAuthFailure()){
                log.error("JFRAME DETECTED AUTH FAILURE");
            }
        }
    }
}
