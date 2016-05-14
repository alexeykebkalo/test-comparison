package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class Cluster {

    private int minx;
    private int miny;
    private int maxx;
    private int maxy;
    private List<ClusteredPixel> clusteredPixels = new ArrayList<>();
    private Color averageColor;
    private int redSum;
    private int greenSum;
    private int blueSum;

    public void addPixel(ClusteredPixel pixel) {
        if (clusteredPixels.isEmpty()) {
            minx = pixel.getX();
            miny = pixel.getY();
            maxx = pixel.getX();
            maxy = pixel.getY();
            averageColor = pixel.getColor();
            redSum = averageColor.getRed();
            greenSum = averageColor.getGreen();
            blueSum = averageColor.getBlue();
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
            Color color = pixel.getColor();
            redSum += color.getRed();
            greenSum += color.getGreen();
            blueSum += color.getBlue();
            int newSize = clusteredPixels.size() + 1;
            averageColor = new Color(redSum / newSize, greenSum / newSize, blueSum / newSize);
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

    public Color getColor() {
        return averageColor;
    }
}
