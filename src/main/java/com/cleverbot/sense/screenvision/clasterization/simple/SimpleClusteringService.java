package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.clasterization.ClusteringService;
import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
@Component
public class SimpleClusteringService implements ClusteringService {

    public List<Image> cluster(Image image) {
        ClusteredPixel[][] clusteredPixels = new ClusteredPixel[image.getWidth()][image.getHeight()];
        List<Cluster> clusters = new ArrayList<Cluster>();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Cluster cluster;
                ClusteredPixel clusteredPixel = new ClusteredPixel();
                Pixel pixel = image.getPixel(x, y);
                clusteredPixel.setPixel(pixel);
                clusteredPixels[x][y] = clusteredPixel;
                if (x > 0 && PixelsComparator.areSimilar(pixel, image.getPixel(x - 1, y))) {
                    cluster = clusteredPixels[x-1][y].getCluster();
                } else if (y > 0 && PixelsComparator.areSimilar(pixel, image.getPixel(x, y - 1))) {
                    cluster = clusteredPixels[x][y - 1].getCluster();
                } else {
                    cluster = new Cluster();
                    clusters.add(cluster);
                }
                clusteredPixel.setCluster(cluster);
                cluster.addPixel(pixel);
            }
        }
        List<Image> images = new ArrayList<Image>();
        for (Cluster cluster : clusters) {
            images.add(cluster.generateImage());
        }
        return images;
    }
}
