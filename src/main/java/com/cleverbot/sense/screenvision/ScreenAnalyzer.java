package com.cleverbot.sense.screenvision;

import com.cleverbot.memory.Entity;
import com.cleverbot.sense.SenseAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
@Component
public class ScreenAnalyzer implements SenseAnalyzer {

    @Autowired
    private ScreenInput screenInput;

    public List<Entity> getEntities() {
        BufferedImage image = screenInput.getImage();
        try {
            ImageIO.write(image, "bmp", new File("input"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
