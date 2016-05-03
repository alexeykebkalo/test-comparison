package com.cleverbot.sense.screenvision;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
@Component
public class ScreenInput {

    public BufferedImage getImage() {
        Rectangle fullScreen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(fullScreen);

        } catch (AWTException e) {
        }
        return image;
    }
}
