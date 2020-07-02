package com.graphic.test.sense.screenvision.clustering.simple;

import com.graphic.test.sense.screenvision.clustering.ClusteringService;
import com.graphic.test.sense.screenvision.model.Image;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
@Component
public class SimpleClusteringService implements ClusteringService {

    public List<Image> clasterize(Image image) {
        ClusteredPixel[][] clusteredPixels = new ClusteredPixel[image.getWidth()][image.getHeight()];
        List<Cluster> clusters = new LinkedList<>();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Cluster cluster = null;
                ClusteredPixel clusteredPixel = new ClusteredPixel(image.getPixel(x, y));
                clusteredPixels[x][y] = clusteredPixel;
                if (x > 0 && PixelsComparator.areSimilar(clusteredPixel, image.getPixel(x - 1, y))) {
                    cluster = clusteredPixels[x-1][y].getCluster();
                }
                if (y > 0 && PixelsComparator.areSimilar(clusteredPixel, image.getPixel(x, y - 1))) {
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
        return clusters.stream().map(Cluster::generateImage).collect(Collectors.toList());
    }
}
