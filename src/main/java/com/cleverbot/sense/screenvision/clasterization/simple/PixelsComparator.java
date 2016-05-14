package com.cleverbot.sense.screenvision.clasterization.simple;

import java.awt.*;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class PixelsComparator {
    private static final int THRESHOLD = 70;
    private static final int THRESHOLD_X2 = THRESHOLD * THRESHOLD;

    public static boolean areSimilar(Color color1, Color color2) {
        int diffRed = color1.getRed() - color2.getRed();
        int diffGreen = color1.getGreen() - color2.getGreen();
        int diffBlue = color1.getBlue() - color2.getBlue();
        return diffRed * diffRed + diffGreen * diffGreen + diffBlue * diffBlue <= THRESHOLD_X2;
    }
}
