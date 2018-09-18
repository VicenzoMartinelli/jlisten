package Music;

import helpers.ExceptionLogger;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;

public class MusicPlay {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int STOPPED = 2;
    private final static int CLOSED = 3;

    private final Player player;
    private final Object playerLock = new Object();

    private int playerStatus = NOTSTARTED;

    public MusicPlay(InputStream inputStream) throws JavaLayerException {

        this.player = new Player(inputStream);
    }

    public MusicPlay(InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.player = new Player(inputStream, audioDevice);
    }

    public void play() throws JavaLayerException {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case STOPPED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = STOPPED;
            }
            return playerStatus == STOPPED;
        }
    }

    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == STOPPED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    public void stop() {
        synchronized (playerLock) {
            playerStatus = CLOSED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != CLOSED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            }
            catch (JavaLayerException e) {
                ExceptionLogger.saveLogException(e);
                break;
            }
            synchronized (playerLock) {
                while (playerStatus == STOPPED) {
                    try {
                        playerLock.wait();
                    }
                    catch (final InterruptedException e) {
                        ExceptionLogger.saveLogException(e);
                        break;
                    }
                }
            }
        }
        close();
    }

    public void close() {
        synchronized (playerLock) {
            playerStatus = CLOSED;
        }
        try {
            player.close();
        }
        catch (Exception e) {
                ExceptionLogger.saveLogException(e);
        }
    }

    public Status getStatus() {
        return Status.values()[this.playerStatus];
    }

}
