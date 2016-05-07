package com.cleverbot.sense.screenvision.model;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class Image {

    private int x;
    private int y;
    private int width;
    private int height;
    private Pixel[][] pixels;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[y][x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        pixels[y][x] = pixel;
    }
}
