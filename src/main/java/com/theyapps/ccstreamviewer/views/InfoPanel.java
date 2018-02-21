package com.theyapps.ccstreamviewer.views;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;

import javax.naming.AuthenticationException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Displays info about the CCTV system.
 */
public class InfoPanel extends JPanel implements MouseListener{
    private Zoneminder m_zm;
    private MainFrame mainFrame;
    private JPanel m_pnlLeft;
    private JPanel m_pnlRight;
    private JLabel m_lblAbout;
    private JLabel m_lblStatus;
    private JLabel m_lblMenu;
    private JLabel m_lblExit;

    private boolean serverRunning;

    public InfoPanel(MainFrame mf){
        m_zm = Zoneminder.getInstance();

        Color bgColor = PropertiesReader.getColor("views.infopanel.backgroundcolor");
        Color fgColor = PropertiesReader.getColor("views.infopanel.foregroundcolor");

        mainFrame = mf;
        serverRunning = false;

        this.setBackground(bgColor);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        m_pnlLeft = new JPanel();
        m_pnlRight = new JPanel();
        m_lblAbout = new JLabel("Server Status ");
        m_lblStatus = new JLabel("?");
        m_lblStatus.setForeground(Color.ORANGE);
        m_lblMenu = new JLabel("\u2630");
        m_lblExit = new JLabel("X");

        setLayout(new BorderLayout());
        m_pnlLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        m_pnlRight.setLayout(new FlowLayout(FlowLayout.TRAILING));

        m_pnlLeft.setBackground(bgColor);
        m_pnlLeft.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_pnlRight.setBackground(bgColor);
        m_pnlRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(m_pnlLeft, BorderLayout.WEST);
        add(m_pnlRight, BorderLayout.EAST);

        m_lblMenu.addMouseListener(this);
        m_lblExit.addMouseListener(this);
        m_lblStatus.addMouseListener(this);

        m_lblAbout.setForeground(fgColor);
        m_lblStatus.setForeground(fgColor);
        m_lblMenu.setForeground(fgColor);
        m_lblExit.setForeground(fgColor);

        m_pnlLeft.add(m_lblAbout);
        m_pnlLeft.add(m_lblStatus);
        m_pnlRight.add(m_lblMenu);
        m_pnlRight.add(m_lblExit);

        Timer statusTimer = new Timer();
        statusTimer.schedule(new PollStatusTask(), 0, 1000);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(m_lblExit)){
            System.exit(0);
        }
        else if(e.getSource().equals(m_lblMenu)){
            // TODO: Implement
        }
        else if(e.getSource().equals(m_lblStatus)){
            m_lblStatus.setText(m_lblStatus.getText() + "...");
            m_zm.setRunningAsync(!m_zm.isRunning());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    class PollStatusTask extends TimerTask{
        @Override
        public void run() {
            boolean thisRun = m_zm.isRunning();
            if(thisRun != serverRunning){
                m_lblStatus.setText(m_zm.isRunning() ? "Running" : "Stopped");
                if(m_lblStatus.getText().equals("Running")){
                    m_lblStatus.setForeground(Color.GREEN);
                }
                else if(m_lblStatus.getText().equals("Stopped")){
                    m_lblStatus.setForeground(Color.RED);
                }
                else {
                    m_lblStatus.setForeground(Color.ORANGE);
                }

                serverRunning = thisRun;
            }
        }
    }
}
