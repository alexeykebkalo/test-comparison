package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.clasterization.ClusteringService;
import com.cleverbot.sense.screenvision.model.Image;
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
                Cluster cluster = null;
                ClusteredPixel clusteredPixel = new ClusteredPixel(image.getPixel(x, y));
                clusteredPixels[x][y] = clusteredPixel;
                if (x > 0 && PixelsComparator.areSimilar(clusteredPixel.getColor(), clusteredPixels[x - 1][y].getCluster().getColor())) {
                    cluster = clusteredPixels[x-1][y].getCluster();
                }
                if (y > 0 && PixelsComparator.areSimilar(clusteredPixel.getColor(), clusteredPixels[x][y - 1].getCluster().getColor())) {
                    Cluster yCluster = clusteredPixels[x][y - 1].getCluster();
                    if (cluster == null) {
                        cluster = yCluster;
                    } else if (cluster != yCluster) {
                        cluster.merge(yCluster);
                        clusters.remove(yCluster);
                    }

                }
                if (cluster == null) {
                    cluster = new Cluster();
                    clusters.add(cluster);
                }
                clusteredPixel.setCluster(cluster);
                cluster.addPixel(clusteredPixel);
            }
        }
        List<Image> images = new ArrayList<Image>();
        for (Cluster cluster : clusters) {
            images.add(cluster.generateImage());
        }
        return images;
    }
}
