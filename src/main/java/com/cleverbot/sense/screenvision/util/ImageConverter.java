package com.cleverbot.sense.screenvision.util;

import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class ImageConverter {

    public static Image convertFromBufferedImage(BufferedImage bufferedImage) {
        Image image = new Image();
        image.setX(0);
        image.setY(0);
        image.setWidth(bufferedImage.getWidth());
        image.setHeight(bufferedImage.getHeight());
        final byte[] originalPixels = ((DataBufferByte) bufferedImage.getData().getDataBuffer()).getData();
        int offset = 0;
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int red = originalPixels[offset++];
                int green = originalPixels[offset++];
                int blue = originalPixels[offset++];
                image.setPixel(x, y , new Pixel(x, y, new Color(red, green, blue, 0)));
            }
        }
        return image;
    }
}
