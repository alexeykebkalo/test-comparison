package com.graphic.test.sense.screenvision.clustering.simple;

import com.graphic.test.sense.screenvision.model.Pixel;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public class PixelsComparator {
    private static final int THRESHOLD = 70;
    private static final int THRESHOLD_X2 = THRESHOLD * THRESHOLD;

    public static boolean areSimilar(Pixel pixel1, Pixel pixel2) {
        int diffRed = pixel1.getColor().getRed() - pixel2.getColor().getRed();
        int diffGreen = pixel1.getColor().getGreen() - pixel2.getColor().getGreen();
        int diffBlue = pixel1.getColor().getBlue() - pixel2.getColor().getBlue();
        return diffRed * diffRed + diffGreen * diffGreen + diffBlue * diffBlue <= THRESHOLD_X2;
    }
}
