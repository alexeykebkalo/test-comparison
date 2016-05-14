package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.model.Pixel;

import java.awt.*;

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
