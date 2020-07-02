package com.graphic.test.sense.screenvision.clustering.simple;

import com.graphic.test.sense.screenvision.model.Pixel;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class ClusteredPixel extends Pixel {

    private Cluster cluster;

    public ClusteredPixel(Pixel pixel) {
        super(pixel.getX(), pixel.getY(), pixel.getColor());
        cluster = null;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
