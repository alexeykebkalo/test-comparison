package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.model.Pixel;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class ClusteredPixel {

    private Pixel pixel;
    private Cluster cluster;

    public Pixel getPixel() {
        return pixel;
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
