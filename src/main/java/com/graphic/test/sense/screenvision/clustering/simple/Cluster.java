package com.graphic.test.sense.screenvision.clustering.simple;

import com.graphic.test.sense.screenvision.model.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class Cluster {

    private int minx;
    private int miny;
    private int maxx;
    private int maxy;
    private List<ClusteredPixel> clusteredPixels = new LinkedList<>();

    public void addPixel(ClusteredPixel pixel) {
        if (clusteredPixels.isEmpty()) {
            minx = pixel.getX();
            miny = pixel.getY();
            maxx = pixel.getX();
            maxy = pixel.getY();
        } else {
            if (pixel.getX() < minx) {
                minx = pixel.getX();
            } else if (pixel.getX() > maxx) {
                maxx = pixel.getX();
            }
            if (pixel.getY() < miny) {
                miny = pixel.getY();
            } else if (pixel.getY() > maxy) {
                maxy = pixel.getY();
            }
        }
        clusteredPixels.add(pixel);
    }

    public void merge(Cluster cluster) {
        for (ClusteredPixel clusteredPixel : cluster.clusteredPixels) {
            clusteredPixel.setCluster(this);
            addPixel(clusteredPixel);
        }
    }

    public Image generateImage() {
        Image image = new Image(minx, miny, maxx - minx + 1, maxy - miny + 1);
        for (ClusteredPixel pixel : clusteredPixels) {
            pixel.setX(pixel.getX() - minx);
            pixel.setY(pixel.getY() - miny);
            image.setPixel(pixel);
        }
        return image;
    }
}
