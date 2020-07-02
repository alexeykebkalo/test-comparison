package com.graphic.test.sense.screenvision;

import com.graphic.test.sense.screenvision.util.ImageUtil;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
@Component
public class ScreenInput {

	private Robot robot;

	public ScreenInput() throws AWTException {
		robot = new Robot();
	}

	public BufferedImage capture() {
		Rectangle fullScreen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		return robot.createScreenCapture(fullScreen);
    }
}
