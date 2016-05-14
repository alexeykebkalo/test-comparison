package com.cleverbot.sense.screenvision;

import com.cleverbot.memory.Entity;
import com.cleverbot.sense.SenseAnalyzer;
import com.cleverbot.sense.screenvision.clasterization.ClusteringService;
import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.util.ImageConverter;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
@Component
public class ScreenAnalyzer implements SenseAnalyzer {

    private final static Logger LOG = Logger.getLogger(ScreenAnalyzer.class);

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
        LOG.info("Received image. Generating clusters...");
        List<Image> clusters = clusteringService.cluster(image);
        LOG.info(String.format("Image has been processed. Received %d clusters", clusters.size()));

        int counter = 0;
        for (Image cluster : clusters) {
            BufferedImage outputImage = ImageConverter.convertToBufferedImage(cluster);
            String fileName = String.format("clusters/cluster%04d.bmp", ++counter);
            try {
                ImageIO.write(outputImage, "bmp", new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info(String.format("Cluster number %d was saved into file %s", counter, fileName));
        }
        return null;
    }

    public void saveImage(String fileName) {
        BufferedImage image = screenInput.getImage();
        try {
            ImageIO.write(image, "bmp", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
