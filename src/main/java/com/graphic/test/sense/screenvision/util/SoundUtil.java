package com.graphic.test.sense.screenvision.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SoundUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoundUtil.class);

	public static byte[] readSound(String fileName) {
		File file = new File(fileName);
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			byte[] sound = new byte[(int) file.length()];
			fileInputStream.read(sound);
			return sound;
		} catch (Exception e) {
			LOGGER.error("Cannot read sound data from file " + fileName, e);
			return null;
		}
	}

	public static void playSoundFromFile(String fileName) {
		playSound(readSound(fileName));
	}

	public static void playSound(byte[] sound) {
		if (sound == null) {
			LOGGER.error("Cannot play null sound");
			return;
		}
		InputStream inputStream = new ByteArrayInputStream(sound);
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(inputStream);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			LOGGER.error("Error when playing sound", e);
		}
	}
}
