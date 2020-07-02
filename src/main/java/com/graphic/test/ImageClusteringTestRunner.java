package com.graphic.test;

import com.graphic.test.sense.screenvision.clustering.ClusteringService;
import com.graphic.test.sense.screenvision.model.Image;
import com.graphic.test.sense.screenvision.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
public class ImageClusteringTestRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageClusteringTestRunner.class);

    private final static String OUTPUT_FILE_FORMAT = "cluster%5d.bmp";

    public static void main(String [] args) throws Exception {
        String inputFile = null;
        if (args.length < 1) {
            LOGGER.error("Missing parameter: input file name");
            System.exit(1);
        } else {
            inputFile = args[0];
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ClusteringService clusteringService = context.getBean(ClusteringService.class);

        BufferedImage inputImage = ImageUtil.readImage(inputFile);
        List<Image> clusteredImages = clusteringService.clasterize(ImageUtil.convertFromBufferedImage(inputImage));

        int counter = 0;
        for (Image image :clusteredImages) {
            try {
                ImageUtil.saveImage(image, String.format(OUTPUT_FILE_FORMAT, ++counter));
            } catch (IOException e) {
                LOGGER.error("Error saving image " + counter, e);
            }
        }
    }
}
