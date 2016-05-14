package com.cleverbot;

import com.cleverbot.sense.screenvision.ScreenAnalyzer;
import com.sun.istack.internal.logging.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
public class Main {
    private final static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String [] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ScreenAnalyzer screenAnalyzer = context.getBean(ScreenAnalyzer.class);
        if (args.length > 0 && "capture".equals(args[0])) {
            performCapture(screenAnalyzer);
        } else {
            screenAnalyzer.getEntities();
        }
    }

    private static void performCapture(ScreenAnalyzer screenAnalyzer) throws Exception {
        final int startingDelay = 30000;
        final int delay = 1000;
        final int maxNumber = 500;
        byte[] sound = readSound("chord.wav");
        for (int i = startingDelay / 1000; i > 0; i--) {
            LOG.info(String.format("Starting capture in %d sec", i));
            if (i <= 10) {
                playSoundFromFile(i + ".wav");
            }
            Thread.sleep(1000);
        }
        int number = 0;
        while (number < maxNumber) {
            String fileName = String.format("capture/image%05d.bmp", number);
            screenAnalyzer.saveImage(fileName);
            LOG.info(String.format("Captured screen #%d", number));
            number++;
            playSound(sound);
            Thread.sleep(delay);
        }
    }

    private static byte[] readSound(String fileName) throws Exception {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] sound = new byte[(int) file.length()];
        fileInputStream.read(sound);
        fileInputStream.close();
        return sound;
    }

    private static void playSoundFromFile(String fileName) throws Exception {
        playSound(readSound(fileName));
    }

    private static void playSound(byte[] sound) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(sound);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(inputStream);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
