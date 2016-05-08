package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;

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
    private List<Pixel> pixels = new ArrayList<Pixel>();

    public void addPixel(Pixel pixel) {
        if (pixels.isEmpty()) {
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
        pixels.add(pixel);
    }

    public Image generateImage() {
        Image image = new Image(minx, miny, maxx - minx + 1, maxy - miny + 1);
        for (Pixel pixel : pixels) {
            pixel.setX(pixel.getX() - minx);
            pixel.setY(pixel.getY() - miny);
            image.setPixel(pixel);
        }
        return image;
    }
}
