package com.graphic.test;

import com.graphic.test.sense.screenvision.ScreenInput;
import com.graphic.test.sense.screenvision.util.ImageUtil;
import com.graphic.test.sense.screenvision.util.SoundUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.image.BufferedImage;

public class DelayedScreenCaptureRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DelayedScreenCaptureRunner.class);

	private static final int INITIAL_DELAY = 30000;
	private static final int COUNTDOWN_THRESHOLD = 10;
	private static final int COUNTDOWN_DELAY = 1000;
	private static final int CAPTURE_DELAY = 1000;

	private static final int MAX_SCREENSHOTS = 1000;

	private static final String CAPTURE_SOUND_FILE = "chord.wav";
	private static final String COUNTDOWN_FILE_FORMAT = "%d.wav";

	private static final String OUTPUT_FILE_FORMAT = "%5d.bmp";

	public static void main(String [] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		ScreenInput screenInput = context.getBean(ScreenInput.class);

		byte[] captureSound = SoundUtil.readSound(CAPTURE_SOUND_FILE);

		delayBeforeStart();

		for (int captureNumber = 0; captureNumber < MAX_SCREENSHOTS; captureNumber++) {
			try {
				BufferedImage image = screenInput.capture();
				ImageUtil.saveImage(image, String.format(OUTPUT_FILE_FORMAT, captureNumber));
				LOGGER.info(String.format("Captured screen #%d", captureNumber));
				SoundUtil.playSound(captureSound);
			} catch (Exception e) {
				LOGGER.info("Error when capturing screen " + captureNumber, e);
			}
			Thread.sleep(CAPTURE_DELAY);
		}
	}

	private static void delayBeforeStart() throws InterruptedException {
		for (int i = INITIAL_DELAY / COUNTDOWN_DELAY; i > 0; i--) {
			LOGGER.info(String.format("Starting capture in %d sec", i));
			if (i <= COUNTDOWN_THRESHOLD) {
				SoundUtil.playSoundFromFile(String.format(COUNTDOWN_FILE_FORMAT, i));
			}
			Thread.sleep(COUNTDOWN_DELAY);
		}
	}
}
