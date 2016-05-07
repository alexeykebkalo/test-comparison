package com.cleverbot.sense.screenvision.clasterization;

import com.cleverbot.sense.screenvision.model.Image;

import java.util.List;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 07.05.2016.
 */
public interface ClusteringService {

    List<Image> cluster(Image image);
}
