package com.cleverbot.sense.screenvision.util;

import com.cleverbot.sense.screenvision.model.Image;
import com.cleverbot.sense.screenvision.model.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class ImageConverter {

    public static Image convertFromBufferedImage(BufferedImage bufferedImage) {
        Image image = new Image(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        final byte[] originalPixels = ((DataBufferByte) bufferedImage.getData().getDataBuffer()).getData();
        int offset = 0;
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int blue = originalPixels[offset++] & 0xFF;
                int green = originalPixels[offset++] & 0xFF;
                int red = originalPixels[offset++] & 0xFF;
                image.setPixel(new Pixel(x, y, new Color(red, green, blue, 0)));
            }
        }
        return image;
    }

    public static BufferedImage convertToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getPixel(x, y) != null) {
                    bufferedImage.setRGB(x, y, image.getPixel(x, y).getColor().getRGB());
                }
            }
        }
        return bufferedImage;
    }

    public static BufferedImage convertToBufferedImageWithOffset(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth() + image.getStartX(), image.getHeight() + image.getStartY(), TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getPixel(x, y) != null) {
                    bufferedImage.setRGB(x + image.getStartX(), y + image.getStartY(), image.getPixel(x, y).getColor().getRGB());
                }
            }
        }
        return bufferedImage;
    }
}
