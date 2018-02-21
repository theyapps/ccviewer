package com.theyapps.ccstreamviewer.views;

import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;
import org.apache.log4j.Logger;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import static uk.co.caprica.vlcj.player.Marquee.marquee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class VideoPanel extends JPanel implements MouseListener {
    private static Logger log = Logger.getLogger(VideoPanel.class);

    private ZMMonitor zmMonitor;
    private MediaPlayerFactory mpFactory;
    private EmbeddedMediaPlayer embPlayer;
    private Canvas canvas;
    private CanvasVideoSurface vidSurface;
    private MainFrame mainFrame;

    public VideoPanel(MainFrame mf, ZMMonitor monitor) {
        zmMonitor = monitor;
        mainFrame = mf;

        // Create canvas
        canvas = new Canvas();

        // Create Media Player
        mpFactory = new MediaPlayerFactory();
        embPlayer = mpFactory.newEmbeddedMediaPlayer();
        vidSurface = mpFactory.newVideoSurface(canvas);
        embPlayer.setVideoSurface(vidSurface);

        //add(new JLabel(monitor.getPath()));
        add(canvas);
        canvas.setBackground(Color.BLACK);
        canvas.setSize(600, 338);

        setBackground(PropertiesReader.getColor("views.videopanel.inactivecolor"));
        addMouseListener(this);

        revalidate();
        repaint();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean play(){
        log.info("Playing: " + zmMonitor.getName());
        return embPlayer.playMedia(zmMonitor.getPath());
    }

    public void setAlarm(boolean alarm){
        if (alarm){
            setBackground(PropertiesReader.getColor("views.videopanel.activecolor"));
        }
        else {
            setBackground(PropertiesReader.getColor("views.videopanel.inactivecolor"));
        }
    }

    public ZMMonitor getZmMonitor() {
        return zmMonitor;
    }

    public void setZmMonitor(ZMMonitor zmMonitor) {
        this.zmMonitor = zmMonitor;
    }

    @Override
    public void addMouseListener(MouseListener l){
        super.addMouseListener(l);
        canvas.addMouseListener(l);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*if(embPlayer.isPlaying()){
            embPlayer.pause();
        }
        else {
            embPlayer.play();
        }
        log.info("Left mouse click.");*/
        //embPlayer.toggleFullScreen();
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
}
