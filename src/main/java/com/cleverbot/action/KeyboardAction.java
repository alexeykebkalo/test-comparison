package com.cleverbot.action;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Alexey Kebkalo akebkalo@gmail.com on 03.05.2016.
 */
public class KeyboardAction implements ActionExecutor {

    private int key;

    public void perform() {
        try {
            Robot robot = new Robot();

            robot.keyPress(key);
            robot.keyRelease(key);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
