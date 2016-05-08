package com.cleverbot.sense.screenvision.model;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class Image {

    private int startX;
    private int startY;
    private int width;
    private int height;
    private Pixel[][] pixels;

    public Image(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    public void setPixel(Pixel pixel) {
        pixels[pixel.getX()][pixel.getY()] = pixel;
    }
}
