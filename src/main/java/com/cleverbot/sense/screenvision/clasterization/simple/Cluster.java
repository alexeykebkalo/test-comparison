package com.cleverbot.sense.screenvision.clasterization.simple;

import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class Cluster {

    private List<Pixel> pixels = new ArrayList<Pixel>();

    public void addPixel(Pixel pixel) {
        pixels.add(pixel);
    }

    public Image generateImage() {
        return new Image();
    }
}
