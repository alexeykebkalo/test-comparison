package com.cleverbot.sense.screenvision;

import com.cleverbot.memory.Entity;
import com.cleverbot.sense.SenseAnalyzer;
import com.cleverbot.sense.screenvision.clasterization.ClusteringService;
import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.util.ImageConverter;
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

    @Autowired
    private ClusteringService clusteringService;

    public List<Entity> getEntities() {
        //BufferedImage bufferedImage = screenInput.getImage();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("image00003.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = ImageConverter.convertFromBufferedImage(bufferedImage);
        List<Image> clusters = clusteringService.cluster(image);
        return null;
    }

    public void saveImages() {
        for (int i = 0; i < 10; i++) {
            String fileName = String.format("image%05d.bmp", i);
            BufferedImage image = screenInput.getImage();
            try {
                ImageIO.write(image, "bmp", new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
