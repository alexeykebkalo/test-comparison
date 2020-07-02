package com.graphic.test.sense.screenvision.clustering;

import com.graphic.test.sense.screenvision.model.Image;

import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public interface ClusteringService {

    List<Image> clasterize(Image image);
}
